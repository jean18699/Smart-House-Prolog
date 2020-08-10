 /*============================MODULO DE SEGURIDAD===================*/

% El sistema experto recibirá información de sensores de movimiento, y de
% otros tipos, y será alimentado con protocolos de seguridad para
% gestionar funcionalidades de bloqueo y cierre de entradas principales y
% ventanas del hogar, analizando sus estados y modificándolos de acuerdo
% a evaluaciones de eventos específicos que ocurran y que considere
% puedan representar problemas de seguridad para sus habitantes. Un
% ejemplo de esto es que cerraría todas las puertas de acceso al hogar si
% las personas salen o si detecta que todas están durmiendo.

:-dynamic modo_visita/0.

visitantes(on):-not(modo_visita),assertz(modo_visita).
visitantes(off):-modo_visita,retract(modo_visita).



%miembros de la familia:
:-dynamic miembro/1.

%agregar a un nuevo miembro, si ya existe, no lo agrega
nuevo_miembro(Persona):-
    not(miembro(Persona)),
    assertz(miembro(Persona)).

quitar_miembro(Persona):-
    (dormido(Persona), retract(dormido(Persona)));
    (salio(Persona), retract(salio(Persona))).

quitar_miembro(Persona):-
    miembro(Persona),retract(miembro(Persona)),
    retractall(tiene_miembro(_,Persona)).


%listar miembros de la familia.
get_miembros(Miembros):-setof(Miembro, miembro(Miembro), Miembros).

%Miembros de la familia que estan durmiendo:
:-dynamic dormido/1.

dormir(Persona):-
    miembro(Persona), not(dormido(Persona)),
    not(salio(Persona)),assertz(dormido(Persona)).

despertar(Persona):-
    miembro(Persona),dormido(Persona),
    retract(dormido(Persona)).

 %miembros de la familia salieron:
% Aqui hay que ver que si una persona salio, no puede estar durmiendo y
% viceversa.

:-dynamic salio/1.

salir(Persona):-
    miembro(Persona), not(salio(Persona)),
    not(dormido(Persona)),assertz(salio(Persona)).

volver(Persona):-
    miembro(Persona),salio(Persona),
    retract(salio(Persona)).

%control de puertas
:-dynamic puerta/1.
:-dynamic puerta_abierta/1.
:-dynamic puerta_cerrada/1.


nueva_puerta(Puerta):-
    not(puerta(Puerta)), assertz(automatico(Puerta)),
    assertz(puerta_abierta(Puerta)),assertz(puerta(Puerta)).

quitar_puerta(Puerta):-
    puerta(Puerta),
    ((automatico(Puerta), retract(automatico(Puerta)));
    (manual(Puerta), retract(manual(Puerta)))),
    retract(puerta(Puerta)).

abrir_puerta(Puerta):-
    puerta(Puerta),not(puerta_abierta(Puerta)),
    retract(puerta_cerrada(Puerta)),assertz(puerta_abierta(Puerta)).

cerrar_puerta(Puerta):-
    puerta(Puerta),not(puerta_cerrada(Puerta)),
    retract(puerta_abierta(Puerta)),assertz(puerta_cerrada(Puerta)).


get_puertas_automaticas(Puertas):-findall(Puerta,(puerta(Puerta),automatico(Puerta)),Puertas).







/*======================================================================CONTROL DE LOS DISPOSITIVOS DEL HOGAR========================================================================*/

% Capacidad de cambiar el modo de uso de un electronico, controlado por
% la casa o por la persona
modo_manual(Electronico):- %Para los electronicos
    electronico(Electronico),!,not(manual(Electronico)),!,
    retract(automatico(Electronico)),
    assertz(manual(Electronico)).

modo_manual_puerta(Puerta):- %Para las puertas
    puerta(Puerta),!,not(manual(Puerta)),!,
    retract(automatico(Puerta)),
    assertz(manual(Puerta)).

modo_automatico(Electronico):-
    electronico(Electronico),!, not(automatico(Electronico)),!,
    retract(manual(Electronico)), %ya no es manual
    assertz(automatico(Electronico)). %ahora es automatico

modo_automatico_puerta(Puerta):- %Para las puertas
    puerta(Puerta),!, not(automatico(Puerta)),!,
    retract(manual(Puerta)), %ya no es manual
    assertz(automatico(Puerta)). %ahora es automatico

/*FUNCIONES DEL HOGAR SEGUN LOS SENSORES */

% Cerrar puertas y ventanas en el caso de que todos hayan salido o esten
% durmiendo. Ademas, solo se cierran las puertas automaticas
alerta_puertas():- not(modo_visita),(get_miembros(Miembros), todos_dormidos(Miembros)),!,
    findall(Puerta,puerta(Puerta),Puertas),cerrar_puertas_automaticas(Puertas).

alerta_puertas():-not(modo_visita), get_miembros(Miembros), todos_salieron(Miembros),!,findall(Puerta,puerta(Puerta),Puertas),cerrar_puertas_automaticas(Puertas).

% revisar recursivamente si todos los miembros de la lista de miembros estan durmiendo
todos_dormidos([]):-true.
todos_dormidos([Cabeza|_]):- not(dormido(Cabeza)),!,fail.
todos_dormidos([_|Cola]):- todos_dormidos(Cola).

% revisar recursivamente si todos los miembros de la lista de miembros
% estan salieron de la casa
todos_salieron([]):-true.
todos_salieron([Cabeza|_]):- not(salio(Cabeza)),!,fail.
todos_salieron([_|Cola]):- todos_salieron(Cola).

cerrar_puertas_automaticas([]):-!.
cerrar_puertas_automaticas([H|L]):-puerta(H),automatico(H),cerrar_puerta(H),cerrar_puertas_automaticas(L).
cerrar_puertas_automaticas([_|L]):-cerrar_puertas_automaticas(L).










/*=============================================================MODULO DE RECURSOS================================================================================================*/

:-dynamic electronico/1. %electronico(nombreElectronico).
:-dynamic automatico/1. %automatico(nombreElectronico).
:-dynamic manual/1. %manual(nombreElectronico).
:-dynamic encendido/1. %encendido(nombreElectronico).
:-dynamic apagado/1. %apagado(nombreElectronico).
:-dynamic consumo/2. %consumo(Nombre,Consumo).
:-dynamic llave/1. %llave(baño1). -> llave de agua.
:-dynamic llave_abierta/1.
:-dynamic llave_cerrada/1.

nuevo_electronico(Nombre,Consumo):-
    not(electronico(Nombre)),
    assertz(electronico(Nombre)),
    assertz(automatico(Nombre)),
    assertz(apagado(Nombre)),
    assertz(consumo(Nombre,Consumo)). %Los electronicos son automaticos por defecto

quitar_electronico(Electronico):-
    electronico(Electronico),
    retract(electronico(Electronico)),
    retract(consumo(Electronico,_)),
    (manual(Electronico), retract(manual(Electronico)));
    (automatico(Electronico), retract(automatico(Electronico))),
    (encendido(Electronico), retract(encendido(Electronico)));
    (apagado(Electronico), retract(apagado(Electronico))),
    retractall(tiene_electronico(_,Electronico)).

encender(Electronico):-
    electronico(Electronico),
    not(encendido(Electronico)),
    retract(apagado(Electronico)), assertz(encendido(Electronico)).


apagar(Electronico):-
    electronico(Electronico),
    not(apagado(Electronico)),
    retract(encendido(Electronico)), assertz(apagado(Electronico)).

get_consumo_encendidos(Consumos):-findall(Consumo, (consumo(Electronico,Consumo),encendido(Electronico)),Consumos).

% regla que usaremos para, como bien dice su nombre, sumar elementos en
% una lista
suma_lista([],0).
suma_lista([H|L],Total):-suma_lista(L,Con), Total is Con + H.

% regla para devolver el total de consumo de todos los elementos
% encendidos
get_total_consumo(Total):- get_consumo_encendidos(Consumos),suma_lista(Consumos,Total).

% calculo del consumo mensual de agua estimado
% segun la cantidad de personas que habiten la casa.

% Se investigo lo siguiente:

%1 m3 = 1.000 litros
%1 litro = 4 vasos llenos
%1 m3 = 4.000 vasos.

%El consumo promedio mensual de agua por
% persona es de máximo 4 m3. Multiplica este valor por la cantidad de
% personas que viven en tu casa y obtendrás el consumo promedio mensual
% del hogar.

get_consumo_total_agua(Total):- var(Total),
    get_miembros(Miembros),length(Miembros,Cant),
    Total is Cant * 4000. %quiero m3 en litros, asi que 4 x 1000 litros = 4000 litros promedio por persona

% Ahora queremos saber el costo de agua en dolares segun los litros, y
% para esto primero lo transformamos a galones internacionales o
% estadounidenes:

%1 galon internacional = 3,785 litros
%1 litro USA = 0.2642 galones

%Entonces la formula: TotalLitros * 0.2642.

litro_a_galon(Litros,Galones):- (integer(Litros);float(Litros)),
    Galones is Litros * 0.2642,!.

% en USA 4.70$ por 1000 galones. Asi que con las funcion creada de litro
% a galon obtendre los galones, saco el piso en el caso de que salga
% algun valor como 1215 galones para obtener 1000 galones exactos y
% multiplicarlos por 4.70 dolares.

get_costos_agua(Costo):-
    get_consumo_total_agua(Total),litro_a_galon(Total,Galones),
    CantidadMilGalones is Galones / 1000, floor(CantidadMilGalones,G), Costo is 4.70 * G.

% Agregando configuracion de dispositivos que reaccionen a si hay
% personas o no en la habitacion
:-dynamic zonaCasa/1.
:-dynamic tiene_electronico/2.
:-dynamic tiene_miembro/2.

nuevo_lugar(Nombre):- atom(Nombre),
    not(zonaCasa(Nombre)),assertz(zonaCasa(Nombre)).


quitar_lugar(Nombre):- atom(Nombre),
    zonaCasa(Nombre),retract(zonaCasa(Nombre)),
    (tiene_miembro(Nombre,_), retractall(tiene_miembro(Nombre,_)));
    (tiene_electronico(Nombre,_),retractall(tiene_electronico(Nombre,_))).


agregar_electronico_lugar(Electro,Lugar):- atom(Electro), atom(Lugar),
    electronico(Electro),zonaCasa(Lugar),
    not(tiene_electronico(_,Electro)),
    assertz(tiene_electronico(Lugar,Electro)).

quitar_electronico_lugar(Electro,Lugar):- atom(Electro), atom(Lugar),
    electronico(Electro),zonaCasa(Lugar),
    tiene_electronico(Lugar,Electro),
    retract(tiene_electronico(Lugar,Electro)).

get_electronicos_lugar(Lugar,Electronicos):- atom(Lugar),var(Electronicos),
    findall(Electro,tiene_electronico(Lugar,Electro),Electronicos).

tiene_electronicos(Lugar):-
    get_electronicos_lugar(Lugar,Electronicos),
    length(Electronicos,T), T > 0.


agregar_miembro_lugar(Miembro, Lugar):-
    miembro(Miembro), zonaCasa(Lugar),
    not(tiene_miembro(_, Miembro)),
    assertz(tiene_miembro(Lugar, Miembro)).

quitar_miembro_lugar(Miembro, Lugar):-
    miembro(Miembro), zonaCasa(Lugar),
    tiene_miembro(Lugar, Miembro),
    retract(tiene_miembro(Lugar, Miembro)).

get_miembros_lugar(Lugar, Miembros):-
    findall(Miembro, tiene_miembro(Lugar, Miembro), Miembros).

tiene_miembros(Lugar):-
    get_miembros_lugar(Lugar, Miembros),
    length(Miembros, T), T > 0.


apagar_dispositivos([]):-!.
apagar_dispositivos([H|_]):-not(apagado(H)),automatico(H),apagar(H).
apagar_dispositivos([_|L]):-apagar_dispositivos(L).

% para fines de simulacion: revisar todos los lugares y si tienen
% miembros. Si no los tienen, apaga todos los dispositivos que esten
% asociados a esa zona si son automaticos

alerta_nadie_lugar(Lugar):-
    not(tiene_miembros(Lugar)),tiene_electronicos(Lugar),
    get_electronicos_lugar(Lugar,Electronicos),apagar_dispositivos(Electronicos).

get_all_lugares(Lugares):-findall(Lugar,zonaCasa(Lugar),Lugares).

check_all_lugares([]):-!.
check_all_lugares([H|L]):-alerta_nadie_lugar(H),check_all_lugares(L).
check_all_lugares([_|L]):-check_all_lugares(L).

apagar_electronicos_automaticos([]):-!.
apagar_electronicos_automaticos([H|L]):- automatico(H),apagar(H),apagar_electronicos_automaticos(L).
apagar_electronicos_automaticos([_|L]):-apagar_electronicos_automaticos(L).


% regla por si se quieren revisar todos los lugares a la vez y si estan
% vacios
alerta_nadie_todos_lugares():-
     get_all_lugares(Lugares),check_all_lugares(Lugares),!.


% Apagar electronicos en el caso de que todos hayan salido o esten
% durmiendo. Ademas, solo se apagan los electronicos automaticos
alerta_electronicos():- not(modo_visita),(get_miembros(Miembros), todos_dormidos(Miembros)),!,
    findall(Electro,electronico(Electro),Electronicos),apagar_electronicos_automaticos(Electronicos).

alerta_electronicos():-not(modo_visita), get_miembros(Miembros), todos_salieron(Miembros),!,
     findall(Electro,electronico(Electro),Electronicos),apagar_electronicos_automaticos(Electronicos).


% Conocer el momento en el que se encuentra el dia. Asi se podra tambien
% controlar los dispositivos:
:-dynamic horaDiaActual/1.
:-dynamic temporizado/2. %hecho que recibe un electronico y un numero que indica la hora a la que quieres que se apague

cambiar_hora(Hora):-integer(Hora),
    (horaDiaActual(_), retract(horaDiaActual(_)),assertz(horaDiaActual(Hora)),check_electronicos_temporizados(),!);
    assertz(horaDiaActual(Hora)),check_electronicos_temporizados().

verificar_momento_dia(Momento):- var(Momento), horaDiaActual(Hora),
    Hora < 24,
    ((Hora >= 6 , Hora < 12),Momento = 'mañana',!).

verificar_momento_dia(Momento):- var(Momento), horaDiaActual(Hora),
    Hora < 24,
    ((Hora >= 12 , Hora =< 18),Momento = 'tarde',!).

verificar_momento_dia(Momento):- var(Momento), horaDiaActual(Hora),
    Hora < 24, (Momento = 'noche',!).


sugerencia_puertas(Mensaje):- var(Mensaje),
     verificar_momento_dia(Momento),
    (Momento = 'mañana', Mensaje = 'Buen momento para abrir las puertas',!).

sugerencia_puertas(Mensaje):- var(Mensaje),
     verificar_momento_dia(Momento),
    (Momento = 'tarde', Mensaje = 'No se recomienda nada en particular',!).

sugerencia_puertas(Mensaje):- var(Mensaje),
    verificar_momento_dia(Momento),
    (Momento = 'noche', Mensaje = 'Recuerde cerrar las puertas',!).


% regla para apagar un dispositivo electronico luego de un lapso de
% tiempo

programar_apagado(Electronico,Tiempo):-electronico(Electronico),integer(Tiempo), not(apagado(Electronico)),
    (temporizado(Electronico,_), retract(temporizado(Electronico,_)), assertz(temporizado(Electronico,Tiempo)));
    assertz(temporizado(Electronico,Tiempo)).

apagar_temporizados([]):-!.
apagar_temporizados([H|L]):-temporizado(H,Tiempo), horaDiaActual(Hora), (Hora >= Tiempo),apagar(H),retract(temporizado(H,Tiempo)),apagar_temporizados(L).
apagar_temporizados([_|L]):-apagar_temporizados(L).


check_electronicos_temporizados():-findall(Elec, electronico(Elec), Electronicos),apagar_temporizados(Electronicos),!.


%control ambiental
:-dynamic temperaturaHogar/1.

%Segun lo investigado: La temperatura de confort se sitúa entre los
% 18°C y los 21°C para una persona en reposo y entre los 16°C y los 18°C
% para una persona activa.

nueva_temperatura(Temp):-integer(Temp),
    (temperaturaHogar(_), retract(temperaturaHogar(_)),assertz(temperaturaHogar(Temp)),!);
    assertz(temperaturaHogar(Temp)).


%Si todas las personas duermen, se regula la temperatura a 20 grados
regular_temperatura():-
    get_miembros(Miembros), todos_dormidos(Miembros),
    nueva_temperatura(20),!.

%De lo contrario, se regula la temperatura a 17 grados.
regular_temperatura():- nueva_temperatura(17).



%FUENTES DE ENERGIA RENOVABLE

% La mas comun son los paneles solares por lo que solo se usara esa. La
% casa tendra un modo indique cuando se quiere usar las fuentes
% renovables o no y dara sugerencias.

:-dynamic panel_solar/4. %panel_solar(nombre,orientacion,angulo,energia producida).
:-dynamic modo_renovable/0. %activar el uso de los paneles.

usar_paneles(on):-not(modo_renovable),assertz(modo_renovable),!.
usar_paneles(off):-modo_renovable,retract(modo_renovable).


posicion_sol_tiempo(6,sur,15). %hora, orientacion, angulo
posicion_sol_tiempo(7,sur,20).
posicion_sol_tiempo(8,sur,30).
posicion_sol_tiempo(9,sur,45).
posicion_sol_tiempo(10,sur,60).
posicion_sol_tiempo(11,sur,75).
posicion_sol_tiempo(12,sur,90).
posicion_sol_tiempo(12,norte,90).
posicion_sol_tiempo(13,norte,75).
posicion_sol_tiempo(14,norte,60).
posicion_sol_tiempo(15,norte,45).
posicion_sol_tiempo(16,norte,30).
posicion_sol_tiempo(17,norte,20).
posicion_sol_tiempo(18,norte,15).

nuevo_panel(Nombre,Orientacion,Angulo,Energia):-atom(Nombre),atom(Orientacion), integer(Angulo),integer(Energia),
    not(panel_solar(Nombre,_,_,_)),
    (Angulo >= 0 , Angulo =< 180),assertz(panel_solar(Nombre,Orientacion,Angulo,Energia)),!.

quitar_panel(Nombre):-atom(Nombre),
    panel_solar(Nombre,_,_,_),retract(panel_solar(Nombre,_,_,_)).

ajustar_panel(Nombre,Angulo):-
    panel_solar(Nombre,X,_,Energia),retract(panel_solar(Nombre,X,_,_)),
    assertz(panel_solar(Nombre,X,Angulo,Energia)).

cambiar_orientacion_panel(Nombre,Orientacion):- atom(Nombre),atom(Orientacion),
    panel_solar(Nombre,_,Angulo,Energia),retract(panel_solar(Nombre,_,_,_)),
    assertz(panel_solar(Nombre,Orientacion,Angulo,Energia)).

% Se quiere generar una sugerencia para cada panel para dar el mejor
% angulo con respecto al sol de manera que quede perpendicular a este.

get_posicion_sol(Posicion):-
    horaDiaActual(Hora),posicion_sol_tiempo(Hora,_,Posicion).

% A estos hechos les ingreso un panel y me devolvera la mejor posicion
% para que sea perpendicular con respecto al sol, ya sea un panel con
% orientacion norte o sur
sugerencia_posicion_perpendicular_panel(Nombre,Posicion):-panel_solar(Nombre,norte,Angulo,_),
    horaDiaActual(Hora),posicion_sol_tiempo(Hora,norte,P),
    PosPerpend is P - 90,
    abs(PosPerpend, AbsPosPerpend),
    AbsPosPerpend = Angulo,
    Posicion = "Estos paneles ya estan en su angulo optimo",!,fail.

sugerencia_posicion_perpendicular_panel(Nombre,Posicion):-panel_solar(Nombre,norte,_,_),
    horaDiaActual(Hora),posicion_sol_tiempo(Hora,norte,P),
    PosPerpend is P - 90,
    abs(PosPerpend, AbsPosPerpend),
    Posicion = AbsPosPerpend,!.


sugerencia_posicion_perpendicular_panel(Nombre,Posicion):- panel_solar(Nombre,sur,Angulo,_),
    horaDiaActual(Hora),posicion_sol_tiempo(Hora,sur,P), PosPerpend is P - 90,
    abs(PosPerpend, AbsPosPerpend),
    AbsPosPerpend = Angulo,
    Posicion =  "Estos paneles ya estan en su angulo optimo",!,fail.

sugerencia_posicion_perpendicular_panel(Nombre,Posicion):- panel_solar(Nombre,sur,_,_),
    horaDiaActual(Hora),posicion_sol_tiempo(Hora,sur,P), PosPerpend is P - 90,
    abs(PosPerpend, AbsPosPerpend),
    Posicion = AbsPosPerpend,!.


sugerencia_posicion_perpendicular_panel(_,Posicion):-
    Posicion = "Estos paneles no detectan sol, asi que no hay un angulo recomendado",!.

ajustar_paneles_automaticamente([]):-!.
ajustar_paneles_automaticamente([H|L]):-sugerencia_posicion_perpendicular_panel(H,Recomendacion),integer(Recomendacion),ajustar_panel(H,Recomendacion),ajustar_paneles_automaticamente(L).
ajustar_paneles_automaticamente([_|L]):-ajustar_paneles_automaticamente(L).

optimizar_paneles():-
    findall(Panel,panel_solar(Panel,_,_,_),Paneles),ajustar_paneles_automaticamente(Paneles),!.

%Queremos saber la energia total generada por todos los paneles:

total_energia_generada(Total):-findall(Energia,panel_solar(_,_,_,Energia),Generado),suma_lista(Generado,Total).



% Queremos alertar en el modo de energia renovable si el consumo de
% electronicos sobrepasa a lo generado

sugerencia_energia_utilizada(Sugerencia):-modo_renovable,var(Sugerencia),
    get_total_consumo(Total),total_energia_generada(Energia),
    Energia < Total, Sugerencia = "Los paneles no pueden suplir el consumo actual de los electronicos",!.

sugerencia_energia_utilizada(Sugerencia):-modo_renovable,var(Sugerencia),
    get_total_consumo(Total),total_energia_generada(Energia),
    Energia >= Total, Sugerencia = "Ningun problema de energia",!.

sugerencia_energia_utilizada(Sugerencia):- var(Sugerencia),
     Sugerencia = "Ningun problema de energia".

%MANEJO DE BASURA

:-dynamic zafacon/3. %el zafacon tiene nombre, volumen maximo y la lista de basura
:-dynamic basura/3. %la basura tiene nombre, volumen y el zafacon al que pertenece


nuevo_zafacon(Nombre,VolumenMaximo):-atom(Nombre),number(VolumenMaximo),
    not(zafacon(Nombre,_,_)), assertz(zafacon(Nombre,VolumenMaximo,[])).

quitar_zafacon(Nombre):-atom(Nombre),zafacon(Nombre,_,_),retract(zafacon(Nombre,_,_)),retractall(basura(_,_,Nombre)).

total_almacenado_zafacon(NombreBasurero,Total):-atom(NombreBasurero),zafacon(NombreBasurero,_,_),
    findall(Volumen,basura(_,Volumen,NombreBasurero),Volumenes),suma_lista(Volumenes,Total).

agregar_basura(Zafacon,NombreBasura,Volumen):-
    zafacon(Zafacon,VolumenMaximo,Basuras), %revisando si el zafacon existe
    Volumen =< VolumenMaximo,
    %not(basura(NombreBasura,_,Zafacon)), %revisando si esta basura ya esta en ese zafacon
    findall(Volumen,basura(_,Volumen,Zafacon),Volumenes), %buscando los volumenes de todas las basuras ya dentro del zafacon
    suma_lista(Volumenes,Total),TotalCombinado is Total + Volumen,
    TotalCombinado =< VolumenMaximo, %sumando los volumenes de las basuras que ya estan dentro del zafacon y si el total mas el de la basura que entrare no
                                                                  %supera la capacidad, pues se agrega la basura
    assertz(basura(NombreBasura,Volumen,Zafacon)), %
    append(Basuras,[NombreBasura],NuevaListaBasuras),
    retract(zafacon(Zafacon,VolumenMaximo,Basuras)),assertz(zafacon(Zafacon,VolumenMaximo,NuevaListaBasuras)).

alerta_basura(NombreZafacon,Sugerencia):-atom(NombreZafacon),var(Sugerencia),
    zafacon(NombreZafacon,VolumenMaximo,_),findall(Volumen,basura(_,Volumen,NombreZafacon),Volumenes), suma_lista(Volumenes,Total),
    Total >= VolumenMaximo - 10,
    Sugerencia = "Debe de vaciar este zafacon",!.

alerta_basura(NombreZafacon,Sugerencia):-atom(NombreZafacon),var(Sugerencia),
    zafacon(NombreZafacon,VolumenMaximo,_),findall(Volumen,basura(_,Volumen,NombreZafacon),Volumenes), suma_lista(Volumenes,Total),
    Total < VolumenMaximo - 10,
    Sugerencia = "A este zafacon le queda espacio",!.


vaciar_zafacon(NombreZafacon):-atom(NombreZafacon),zafacon(NombreZafacon,Capacidad,_),
    retractall(basura(_,_,NombreZafacon)),retract(zafacon(NombreZafacon,Capacidad,_)),assertz(zafacon(NombreZafacon,Capacidad,[])).
