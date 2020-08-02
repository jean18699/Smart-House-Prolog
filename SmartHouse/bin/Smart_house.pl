%MODULO DE SEGURIDAD

% El sistema experto recibir� informaci�n de sensores de movimiento, y de
% otros tipos, y ser� alimentado con protocolos de seguridad para
% gestionar funcionalidades de bloqueo y cierre de entradas principales y
% ventanas del hogar, analizando sus estados y modific�ndolos de acuerdo
% a evaluaciones de eventos espec�ficos que ocurran y que considere
% puedan representar problemas de seguridad para sus habitantes. Un
% ejemplo de esto es que cerrar�a todas las puertas de acceso al hogar si
% las personas salen o si detecta que todas est�n durmiendo.

%miembros de la familia:
:-dynamic miembro/1.


%agregar a un nuevo miembro, si ya existe, no lo agrega
nuevo_miembro(Persona):-
    not(miembro(Persona)),
    assertz(miembro(Persona)).

quitar_miembro(Persona):-
    miembro(Persona),retract(Persona).

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
% puerta(sala,abierta). ubicacion y estado. La puerta sera un
% dispositivo electronico que se inicia en automatico
nueva_puerta(Puerta):-
    not(puerta(Puerta)), assertz(automatico(Puerta)),
    assertz(puerta_cerrada(Puerta)),assertz(puerta(Puerta)).

quitar_puerta(Puerta):-
    puerta(Puerta),
    ((automatico(Puerta), retract(automatico(Puerta)));
    (manual(Puerta), retract(manual(Puerta)))),
    retract(puerta(Puerta)).

abrir_puerta(Puerta):-
    puerta(Puerta),not(puerta_abierta(Puerta)),
    retract(puerta_cerrada(Puerta)),assertz(puerta_abierta(puerta)).

cerrar_puerta(Puerta):-
    puerta(Puerta),not(puerta_cerrada(Puerta)),
    retract(puerta_abierta(Puerta)),assertz(puerta_cerrada(puerta)).


/*======================================================================CONTROL DE LOS DISPOSITIVOS DEL HOGAR========================================================================*/

% Capacidad de cambiar el modo de uso de un electronico, controlado por
% la casa o por la persona
modo_manual(Electronico):-
    electronico(Electronico),
    retract(automatico(Electronico)),
    assertz(manual(Electronico)).

modo_automatico(Electronico):-
    electronico(Electronico), not(automatico(Electronico)),
    retract(manual(Electronico)), %ya no es manual
    assertz(automatico(Electronico)). %ahora es automatico

/*FUNCIONES DEL HOGAR SEGUN LOS SENSORES */

% Cerrar puertas y ventanas en el caso de que todos hayan salido o esten
% durmiendo. Ademas, solo se cierran las puertas automaticas
cerrar_puertas_automaticamente():- (get_miembros(Miembros), todos_dormidos(Miembros)),!,
    puerta(Puerta), automatico(Puerta), cerrar_puerta(Puerta).

cerrar_puertas_automaticamente():- get_miembros(Miembros), todos_salieron(Miembros),!,
    puerta(Puerta), automatico(Puerta), cerrar_puerta(Puerta).

% revisar recursivamente si todos los miembros de la lista de miembros estan durmiendo
todos_dormidos([]):-true.
todos_dormidos([Cabeza|_]):- not(dormido(Cabeza)),!,fail.
todos_dormidos([_|Cola]):- todos_dormidos(Cola).


% revisar recursivamente si todos los miembros de la lista de miembros
% estan salieron de la casa
todos_salieron([]):-true.
todos_salieron([Cabeza|_]):- not(salio(Cabeza)),!,fail.
todos_salieron([_|Cola]):- todos_salieron(Cola).



/*=============================================================MODULO DE RECURSOS================================================================================================*/

% Existen dispositivos electronicos que tienen 3 atributos: Nombre,
% consumo y estado (encendido o apagado).

:-dynamic electronico/2.
:-dynamic automatico/1.
:-dynamic manual/1.
:-dynamic encendido/2.
:-dynamic apagado/2.

nuevo_electronico(Nombre,Consumo):-
    not(electronico(Nombre,Consumo)),
    assertz(electronico(Nombre,Consumo)),
    assertz(encendido(Nombre,Consumo)),
    assertz(automatico(Nombre)). %Los electronicos son automaticos por defecto

quitar_electronico(Electronico):-
    electronico(Electronico,_),
    retract(electronico(Electronico,_)),
    (manual(Electronico), retract(manual(Electronico)));
    (automatico(Electronico), retract(automatico(Electronico))).

encender(Electronico):-
    electronico(Electronico,_),
    not(encendido(Electronico,_)),
    retract(apagado(Electronico,_)), assertz(encendido(Electronico)).


apagar(Electronico):-
    electronico(Electronico,_),
    not(apagado(Electronico,_)),
    retract(encendido(Electronico,_)), assertz(apagado(Electronico)).


lista_consumos_activos(Gastado):-findall(Consumo, encendido(_,Consumo),Gastado).

consumo_total([],0).
consumo_total([H|L],Total):- consumo_total(L,Cont), Total is Cont + H.

consumo_total_activo(Total):-lista_consumos_activos(Gastado),consumo_total(Gastado,Total).




