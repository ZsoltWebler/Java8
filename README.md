# Java 8

## Streams

Az egyik leghasznosabb újdonság a Java 8 ban a _Stream_-ek voltak.
Nem összekeverendő a Java I/O streamekkel (_FileInputStream_, stb).

Egyszerűen fogalmazva a streamek, becsomagolják az adat forrásainkat (_List_, _Array_, stb) lehetővé teszik, 
hogy különböző műveleteket hajtsunk végre az adatokon.

**A stream nem tárol adatot, nem egy adatstruktúra és soha nem módosítja az eredeti adat forrást.**

## Optional

A Java 8 egy másik érdekes új funkciója az _Optinal_ osztály. Az Optional
egy csomagoló osztály, amivel a _NullPointerException_-ket tudjuk kezelni.
