# EVALUACION 2 - construcciondesoftware2Britneynino

## Informacion general
- Estudiante(s): Britney Nino (usuario GitHub: britneyNino)
- Rama evaluada: develop
- Commit evaluado: ef520632197b84d118eca1b45d540c19953d1dc3
- Fecha: 2026-04-11

---

## Tabla de calificacion

| # | Criterio | Peso | Puntaje (1-5) | Parcial |
|---|---|---|---|---|
| 1 | Modelado de dominio | 20% | 4 | 0.80 |
| 2 | Modelado de puertos | 20% | 1 | 0.20 |
| 3 | Modelado de servicios de dominio | 20% | 1 | 0.20 |
| 4 | Enums y estados | 10% | 4 | 0.40 |
| 5 | Reglas de negocio criticas | 10% | 1 | 0.10 |
| 6 | Bitacora y trazabilidad | 5% | 2 | 0.10 |
| 7 | Estructura interna de dominio | 10% | 3 | 0.30 |
| 8 | Calidad tecnica base en domain | 5% | 4 | 0.20 |
| | **Total base** | | | **2.30** |

### Calculo
Nota base = (4*20 + 1*20 + 1*20 + 4*10 + 1*10 + 2*5 + 3*10 + 4*5) / 100 = 230 / 100 = **2.30**

---

## Penalizaciones aplicadas

Ninguna penalizacion mayor aplicable.

---

## Nota final
**2.3 / 5.0**

---

## Hallazgos

### Criterio 1 - Modelado de dominio (4/5)
- Buena jerarquia: clase abstracta `Client` base y subclases `CompanyClient`, `NaturalPersonClient`.
- `BankProduct` como clase abstracta base es un buen diseno.
- Entidades: `BankAccount`, `Loan`, `OperationLog`, `Transfer`, `User`.
- Falta: `CuentaBancaria` no tiene numero unico explicito ni relacion con `Client` evidente.
- Falta: no hay entidad `ProductoBancario` concreta ni catalogo de productos.

### Criterio 2 - Modelado de puertos (1/5)
- **No existe ninguna interfaz de puerto en la capa domain.**
- No hay `domain/ports/` ni contratos de salida del dominio.

### Criterio 3 - Servicios de dominio (1/5)
- **No existe ninguna clase de servicio de dominio.**
- Casos de uso no implementados.

### Criterio 4 - Enums y estados (4/5)
- Enums presentes en `domain/model/enums/`: `AccountStatus`, `LoanStatus`, `SystemRole`, `TransferStatus`, `UserStatus`.
- Buena separacion en subcarpeta dedicada `enums/`.
- Falta: `TipoCuenta` (AccountType), `Moneda` (Currency), `TipoPrestamo` (LoanType).

### Criterio 5 - Reglas de negocio criticas (1/5)
- Sin servicios no hay reglas aplicadas en el dominio.

### Criterio 6 - Bitacora y trazabilidad (2/5)
- `OperationLog` existe como entidad.
- Sin puerto ni servicio para la bitacora.

### Criterio 7 - Estructura interna de dominio (3/5)
- Buena organizacion interna del modelo: `abstractmodel/`, `entity/`, `enums/`.
- Falta: `ports/` y `services/` completamente ausentes.

### Criterio 8 - Calidad tecnica (4/5)
- Nomenclatura en ingles consistente.
- Uso correcto de herencia y clases abstractas.
- Sin typos detectados.

---

## Recomendaciones
1. Agregar los enums faltantes: `AccountType`, `Currency`, `LoanType`, `ProductCategory`.
2. Crear `domain/ports/` con interfaces de contrato por agregado.
3. Crear `domain/services/` con todos los servicios de caso de uso requeridos.
4. Agregar validaciones de estado en las entidades (cuenta no operable si BLOQUEADA).
