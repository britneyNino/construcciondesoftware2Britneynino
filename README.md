Construccion de Software 2 - Proyecto Bancario
Estudiante: Britney Nino
Usuario GitHub: britneyNino
Materia: Construccion de Software 2
Rama principal de entrega: develop

Descripcion del proyecto
Sistema bancario desarrollado con arquitectura hexagonal (Ports and Adapters). El proyecto modela el dominio de un banco digital que permite gestionar clientes, cuentas bancarias, prestamos, transferencias y una bitacora de operaciones.

Arquitectura
El proyecto sigue los principios de arquitectura hexagonal:

domain/model - Entidades y objetos de dominio
domain/ports/in - Casos de uso (interfaces de entrada)
domain/ports/out - Contratos de persistencia (interfaces de salida)
domain/services - Implementacion de la logica de negocio
domain/exceptions - Excepciones de dominio


Estructura del proyecto
britneynino/src/main/java/domain/exceptions
britneynino/src/main/java/domain/model/abstractmodel
britneynino/src/main/java/domain/model/entity
britneynino/src/main/java/domain/model/enums
britneynino/src/main/java/domain/ports/in
britneynino/src/main/java/domain/ports/out
britneynino/src/main/java/domain/services

Tecnologias

Java 17
Spring Boot
Lombok
Arquitectura Hexagonal (Ports and Adapters)


Como ejecutar el proyecto
Requisitos previos: Java 17 o superior y Maven instalados.
Paso 1 - Clonar el repositorio:
git clone https://github.com/britneyNino/construcciondesoftware2Britneynino.git
cd construcciondesoftware2Britneynino
git checkout develop
Paso 2 - Compilar el proyecto:
cd britneynino
mvn clean compile
Paso 3 - Ejecutar:
mvn spring-boot:run

Reglas de negocio implementadas

Una cuenta bancaria solo puede operar si esta en estado ACTIVE
No se puede cancelar una cuenta con saldo mayor a cero
Los prestamos siguen el flujo: UNDER_REVIEW, APPROVED o REJECTED, DISBURSED
Solo el Analista Interno puede aprobar o rechazar prestamos
Las transferencias que superan el umbral empresarial requieren aprobacion del Supervisor
Las transferencias en PENDING_APPROVAL por mas de 60 minutos pasan a EXPIRED
Los clientes persona natural deben ser mayores de 18 anos
Toda operacion queda registrada en la bitacora con trazabilidad completa


Convencion de commits

ADD: Agregar nuevos archivos o funcionalidades
CHG: Modificar archivos existentes
FIX: Correccion de errores
DEL: Eliminacion de archivos
Sonnet 4.6