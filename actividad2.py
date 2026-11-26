
# 01. DEFINIR LOS DATOS
numeros = [38, 12, 45, 8, 23, 16, 50, 4]

# 02. ALGORITMO RECURSIVO MERGE SORT

def merge_sort(lista):

    # Caso base
    if len(lista) <= 1:
        return lista

    # Dividir el conjunto
    medio = len(lista) // 2

    izquierda = lista[:medio]
    derecha = lista[medio:]

    # Llamadas recursivas
    izquierda = merge_sort(izquierda)
    derecha = merge_sort(derecha)

    # Combinar los resultados
    return combinar(izquierda, derecha)

# 03. COMBINAR LAS LISTAS

def combinar(izquierda, derecha):

    resultado = []
    i = 0
    j = 0
    while i < len(izquierda) and j < len(derecha):
        if izquierda[i] <= derecha[j]:
            resultado.append(izquierda[i])
            i += 1
        else:
            resultado.append(derecha[j])
            j += 1
    resultado.extend(izquierda[i:])
    resultado.extend(derecha[j:])
    return resultado

# 04. EJECUTAR EL ALGORITMO
resultado = merge_sort(numeros)

# 05. MOSTRAR RESULTADOS

print("Lista original:", numeros)
print("Lista ordenada:", resultado)
