## Principales

* `.` - cualquier caracter
* `\d` - cualquier digito, `\D` es su negación
* `\w` - cualquier letra o digito, `\W` es su negación
* `\s` - cualquier espacio, `\S` es su negación
* `\n` - salto de línea
* `^` - indica el inicio de una cadena
* `$` - indica el final de una cadena
* `|` - un o lógico
* `()` - agrupar caracteres que se pueden usar, entre otras cosas, para reemplazar valores

## Clases compuestas

* `[]` - dentro cualquier clase que se quiera construir. Ej: `[0-9] ~ \d`, `[0-9a-zA-Z] ~ \w`
* `[^(cualquier cosa)]` - negación de una clase. Ej: `[^0-5a-c]`, todo lo que no sea un digito del 0 a 5 o letra de la a a la c

## Delimitadores

* `*` - 0 o más
* `+` - 1 o más
* `?` - 0 o 1 (opcional)
* `[clase]{n,m}?` - haz el match más pequeño posible que cumpla la condición

## Contadores

* `(cualquier cosa){n,m}` - de n a m repeticiones. Ej: `{0,1} ~ ?`
* `(cualquier cosa){n}` - n repeticiones, algunas implementaciones exigen que sea `{n,n}`
* `(cualquier cosa){n,}` - de n repeticiones en adelante

## Ejemplos

* `.*` - todas las líneas
* `[1-8]+` - todas las secuencias repetidas con un caracter entre 1 y 8
* `\d*[a-z]s` - caracter entre a y z seguido de una s con cualquier cantidad de digitos (0 o muchos) antes de él
* `.+?[,\n]` - todos los , que antes tengan cualquier caracter (haciendo los match más pequeños posible)
* `^\D.*$` - todas las líneas que inicien con cualquier cosa que no sea un digito

* `^\[LOG.*\[WARN\].*$` - todos los warnings en logs

* `$ cat results.csv | grep ',3[0-9],'` - las ocurrencias de `,3(digito),` en el archivo de results.csv, comillas se pueden quitar en bash para regex, ej: `$ cat results.csv | grep ^2018`
* `$ cat results.csv | grep ^201 | grep Colombia | grep -i ',fifa world cup,'` - partidos de la decada del 2010 de la fifa world cup (sin tomar en cuenta mayúsculas) donde Colombia haya estado involucrado

## Banderas

* `/(expresion)/bandera`
* `i` - Case insensitive
* `g` - busqueda global (por defecto)
* `m` - multilinea
* `s` - modo "dotall" activado, que permite que . coincida con `\n`
* `u` - soporte completo de unicode
* `y` - Modo adhesivo, busqueda en la posición exacta del texto

## Retos

1. Seleccionar 6 números sin importar un caracter de separación opcional cada 2 números, a menos que sea una letra
   - R: `(\d{2,2}[a-zA-Z]?){3,3}`

2. Seleccionar números de telefono con las siguientes condiciones:
   - Inicia con `+dd1`, `+dd`, o `dd` (opcional)
	- Sigue con 6 repeticiones de: un digito antecedido por un punto (.), un guíon medio (-), o un espacio ( )
	- Opcionalmente finaliza con una p, e, o # seguida de 3 digitos
	- Ej: +57-123456#789, 123456, 56-58-11 son validos
   - R: `^((\+\d{2}1)|(\+\d{2})|\d{2})?([. -]?\d){6}([pe#]\d{3})?$`

3. Seleccionar una url, R1 es solo la dirección principal, R2 es toda la url
   - R1: `https?:\/\/(([\w\.-]+\.\w{2,5}(\.\w{2})?)|localhost:\d+)\/?`
   - R2: `https?:\/\/(([\w\.-]+\.\w{2,5}(\.\w{2})?)|localhost:\d+)\/?\S*`

4. Seleccionar un email
   - R1: `[\w\._\+]{5,50}@\w+\.\w{2,6}(\.\w{2})?`

5. Seleccionar ubicaciones para los siguientes formatos:
	1. -99.205646,19.429707,2275.10
	2. -99 12' 34.08"W, 19 34' 56.98"N
   - Ra: `-?\d{1,3}\.\d{6},\s?-?\d{1,3}\.\d{6},.*`
   - Rb: `-?\d{1,3}\s\d{1,2}'\s\d{1,2}\.\d{2}"[WE],\s?-?\d{1,3}\s\d{1,2}'\s\d{1,2}\.\d{2}"[NS]`

6. Seleccionar un nombre
   - R: `^[À-ÿA-Z][À-ÿA-Za-z]+.*[À-ÿA-Z][À-ÿA-Za-z]+`

7. Seleccionar y reemplazar información de un documento sobre peliculas (movies.dat)

   - R_buscar: `^\d+::([À-ÿ\/!\+#\*·\?\w&\s\$:,\(\)'\.-]+)\s\((\d{4})\)::.*$`

  - R_reemplazarSQL: `INSERT INTO movies (year,title) values($2,'$1');`
   - R_reemplazarObjetosJSON: `{\n"title":"$1", \n"year":"$2"\n},`
   - R_reemplazarCSV: `$1,$2`

8. Seleccionar query variables en direcciones de solicitudes GET
   - R_buscar: `[\?&](\w+)=([^&\n]+)`
   - R_reemplazar: `\n$1=$2`

9. Seleccionar una contraseña con mínimo 8 catacteres que contenga minúsculas, mayúsculas, números y signos especiales
   - R: `^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$`