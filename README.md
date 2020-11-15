# csvDatasetMix
Mezclador de archivos csv.

Este repositorio contiene tres programas para mezclar archivos csv.

# Mixing
Es un mezclador básico que tiene como entrada los archivos cuyos nombres le pasemos como argumento.
Los nombres de los ficheros deben estar completos y deben estar separados entre sí por un espacio.
El resultado será un nuevo fichero que se ubicará en el directorio en el que se haya ejecutado el 
programa y contendrá filas de los ficheros entrantes hasta llegar a un máximo de 1000 representantes.

# MixingAll
En este caso, la entrada van a ser todos los ficheros con extensión csv que se encuentren en la carpeta de este programa. La salida será 
un fichero csv con el resultado de la mezcla que contendrá como máximo el número de filas que especifiquemos como argumento. Si no especificamos
dicho valor por parámetro, el máximo número de filas será 1000. Además, el fichero resultante se guardará en una carpeta llamada setExperiment ubicada
dentro del directorio en el que se sitúe el ejecutable.

# MixingAllNom
El funcionamiento es similar al del programa anterior con la diferencia de que cambia el atributo tipo que viene en la entrada con formato numérico a un formato
nominal con el objetivo de poder evaluar el funcionamiento de diferentes algoritmos de clustering con la herramienta Weka.
