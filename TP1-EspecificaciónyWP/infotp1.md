# Fondo Monetario Común

## Descripción del Proyecto

Este proyecto aborda el problema de la distribución de recursos en un entorno donde los individuos deben apostar todos sus recursos en cada paso temporal. Se estudia la cooperación en un "fondo monetario común" y su impacto en la acumulación de recursos a largo plazo.

El objetivo principal es analizar las estrategias óptimas de apuesta y cooperación, evaluando cómo la participación en el fondo común afecta los recursos individuales.

## Objetivos

- Especificar los procedimientos que rigen el sistema de apuestas y redistribución de recursos.

- Demostrar la correctitud de los algoritmos propuestos.

## Especificaciones Implementadas

- redistribucionDeLosFrutos: Calcula los recursos finales tras la redistribución equitativa del fondo común.

- trayectoriaDeLosFrutosIndividualesALargoPlazo: Actualiza la trayectoria de recursos de cada individuo a lo largo del tiempo.

- trayectoriaExtrañaEscalera: Identifica si en la trayectoria de un individuo hay un único máximo local.

- individuoDecideSiCooperarONo: Modela la decisión de un individuo sobre cooperar o no en el fondo común.

- individuoActualizaApuesta: Determina la mejor estrategia de apuesta en función de los eventos pasados.

## Demostraciones de Correctitud

Se proporciona una demostración formal de la correctitud de la función frutoDelTrabajoPuramenteIndividual, que evalúa el rendimiento de un individuo que juega sin cooperar.