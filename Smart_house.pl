%MODULO DE SEGURIDAD

% El sistema experto recibirá información de sensores de movimiento, y de
% otros tipos, y será alimentado con protocolos de seguridad para
% gestionar funcionalidades de bloqueo y cierre de entradas principales y
% ventanas del hogar, analizando sus estados y modificándolos de acuerdo
% a evaluaciones de eventos específicos que ocurran y que considere
% puedan representar problemas de seguridad para sus habitantes. Un
% ejemplo de esto es que cerraría todas las puertas de acceso al hogar si
% las personas salen o si detecta que todas están durmiendo.

%miembros de la familia:
:-dynamic miembro/1.
nuevo_miembro(Nombre):- get_miembros(Miembros), member(Nombre,Miembros), !, fail.
nuevo_miembro(Nombre):- assertz(miembro(Nombre)).

%listar miembros de la familia.
get_miembros(Miembros):-findall(Miembro, miembro(Miembro), Miembros).

%Miembros de la familia que estan durmiendo:
:-dynamic dormido/1.

dormir(Persona):-
    get_miembros(Miembros), member(Persona,Miembros),assertz(dormido(Persona)).

%miembros de la familia salieron:
% Aqui hay que ver que si una persona salio, no puede estar durmiendo y
% viceversa.

:-dynamic salio/1.

salir(Persona):-
    get_miembros(Miembros), member(Persona,Miembros),
    not(dormido(Persona)),assertz(salio(Persona)).


%Existen dispositivos electronicos que tienen 3 atributos: nombre,
% estado y si es manual o automatico
:-dynamic electronico/1.
%objeto(television, automatico)

:-dynamic automatico/1.
:-dynamic manual/1.

nuevo_electronico(Electronico):-
    not(electronico(Electronico)), %si no estaba agregado
    assertz(electronico(Electronico)),
    assertz(automatico(Electronico)). %Los electronicos son automaticos por defecto

quitar_electronico(Electronico):-
    electronico(Electronico),
    retract(electronico(Electronico)),
    (manual(Electronico), retract(manual(Electronico)));
    (automatico(Electronico), retract(automatico(Electronico))).

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



%encender(Electronico):-electronico(Electronico)







% Cerrar puertas y ventanas en el caso de que todos hayan salido o esten
% durmiendo:
cerrar_puertas_automaticamente():- (get_miembros(Miembros), todos_dormidos(Miembros)),!.
cerrar_puertas_automaticamente():- get_miembros(Miembros), todos_salieron(Miembros),!.


% revisar recursivamente si todos los miembros de la lista de miembros
% estan durmiendo
todos_dormidos([]):-true.
todos_dormidos([Cabeza|_]):- not(dormido(Cabeza)),!,fail.
todos_dormidos([_|Cola]):- todos_dormidos(Cola).


% revisar recursivamente si todos los miembros de la lista de miembros
% estan salieron de la casa
todos_salieron([]):-true.
todos_salieron([Cabeza|_]):- not(salio(Cabeza)),!,fail.
todos_salieron([_|Cola]):- todos_salieron(Cola).




