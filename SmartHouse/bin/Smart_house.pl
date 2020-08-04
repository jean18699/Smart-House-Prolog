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
    miembro(Persona),retract(miembro(Persona)).


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
    (apagado(Electronico), retract(apagado(Electronico))).

encender(Electronico):-
    electronico(Electronico),
    not(encendido(Electronico)),
    retract(apagado(Electronico)), assertz(encendido(Electronico)).


apagar(Electronico):-
    electronico(Electronico),
    not(apagado(Electronico)),
    retract(encendido(Electronico)), assertz(apagado(Electronico)).

get_consumo_encendidos(Consumos):-findall(Consumo, (consumo(Electronico,Consumo),encendido(Electronico)),Consumos).
suma_encendidos([],0).
suma_encendidos([H|L],Total):-suma_encendidos(L,Con), Total is Con + H.

% regla para devolver el total de consumo de todos los elementos
% encendidos
get_total_consumo(Total):- get_consumo_encendidos(Consumos),suma_encendidos(Consumos,Total).


%llaves de agua de la casa
nueva_llave(Nombre):-
    not(llave(Nombre)),
    assertz(llave(Nombre)),
    assertz(llave_cerrada(Nombre)).

quitar_llave(Nombre):-
    llave(Nombre),!,
    retract(llave(Nombre)),!,
    (llave_cerrada(Nombre), retract(llave_cerrada(Nombre)));
    (llave_abierta(Nombre), retract(llave_abierta(Nombre))).

abrir_llave(Nombre):-
    llave(Nombre),
    not(llave_abierta(llave)),
    retract(llave_cerrada(Nombre)),assert(llave_abierta(llave)).

cerrar_llave(Nombre):-
    llave(Nombre),
    llave_abierta(llave),
    retract(llave_abierta(Nombre)),assert(llave_cerrada(llave)).






