# EVALUACIÓN - construcciondesoftware2Britneynino

## Información General
- **Estudiante(s):** Britney Niño (BritneyNi)
- **Rama evaluada:** develop
- **Fecha de evaluación:** 2026-03-23

---

## Tabla de Calificación

| # | Criterio | Peso | Puntaje (1–5) | Nota ponderada |
|---|---|---|---|---|
| 1 | Modelado de dominio | 25% | 3 | 0.75 |
| 2 | Relaciones entre entidades | 15% | 3 | 0.45 |
| 3 | Uso de Enums | 15% | 3 | 0.45 |
| 4 | Manejo de estados | 5% | 3 | 0.15 |
| 5 | Tipos de datos | 5% | 2 | 0.10 |
| 6 | Separación Usuario vs Cliente | 10% | 4 | 0.40 |
| 7 | Bitácora | 5% | 3 | 0.15 |
| 8 | Reglas básicas de negocio | 5% | 2 | 0.10 |
| 9 | Estructura del proyecto | 10% | 4 | 0.40 |
| 10 | Repositorio | 10% | 1 | 0.10 |
| **TOTAL** | | **100%** | | **3.05** |

## Penalizaciones
- **Variables/clases mal nombradas: -10%** → Las anotaciones Lombok están en minúsculas (`@getter`, `@setter`) en lugar de `@Getter`, `@Setter`. Los imports también tienen sintaxis incorrecta (`import.lombok.Getter`). Esto implica errores de compilación y malas prácticas de nombramiento.

## Bonus
- Ninguno aplicado.

## Nota Final: 2.7 / 5.0

> Nota base 3.05 × 0.90 (penalización -10%) = **2.74** → **2.7**

---

## Análisis por Criterio

### 1. Modelado de dominio — 3/5
Entidades presentes: `Client` (abstracta), `CompanyClient`, `NaturalPersonClient`, `User`, `BankAccount`, `Loan`, `Transfer`, `OperationLog`. También existe `BankProduct` como clase abstracta. Faltan entidades como `BankingProduct` como catálogo concreto. Las entidades básicas están cubiertas.

### 2. Relaciones entre entidades — 3/5
`Client` es abstracta con información de contacto. `BankAccount` extiende `BankProduct`. `User` tiene campos de rol y estado vía enums. Sin embargo, no está explícita la relación `User → Client` ni `BankAccount → Client`. Las relaciones son incompletas.

### 3. Uso de Enums — 3/5
Enums presentes: `AccountStatus`, `LoanStatus`, `SystemRole`, `TransferStatus`, `UserStatus`. Faltan: `AccountType`, `LoanType`, `Currency`, `ProductCategory`. `BankAccount` usa `String accountType` en lugar del enum `AccountType`.

### 4. Manejo de estados — 3/5
Los estados existentes usan enums correctamente (`AccountStatus`, `LoanStatus`, `TransferStatus`, `UserStatus`). Pero falta `AccountType` como enum.

### 5. Tipos de datos — 2/5
Se usa `double currentBalance` en `BankAccount` en lugar de `BigDecimal`. Se usa `java.util.Date` en lugar de `java.time.LocalDate`. El estado de cuenta usa `AccountStatus` enum ✓.

### 6. Separación Usuario vs Cliente — 4/5
`User` y `Client` son clases separadas ✓. `Client` es abstracta con `CompanyClient` y `NaturalPersonClient`. Sin embargo, no hay una referencia explícita entre `User` y el `Client` que representa.

### 7. Bitácora — 3/5
`OperationLog` tiene `Map<String, Object> detailData` ✓ y `LocalDateTime`. Sin embargo, el campo `operationType` es `String` en lugar de usar un enum. Los imports y anotaciones tienen errores de sintaxis.

### 8. Reglas básicas de negocio — 2/5
No se observan métodos de negocio ni validaciones en las entidades.

### 9. Estructura del proyecto — 4/5
Estructura clara: `domain/model/entity`, `domain/model/abstractmodel`, `domain/model/enums`. Bien organizado por capas.

### 10. Repositorio — 1/5
- **Nombre:** `construcciondesoftware2Britneynino` — correcto.
- **README:** Solo el nombre del repo. Sin información de materia, integrantes o instrucciones de ejecución.
- **Commits:** No usa formato ADD/CHG. Solo 4 commits con mensajes básicos ("entidades", "entidades").
- **Ramas:** Tiene `develop` ✓.
- **Tag:** No hay tag.

---

## Fortalezas
- Separación `Client` / `User` bien planteada.
- `Client` es clase abstracta con jerarquía (`CompanyClient`, `NaturalPersonClient`).
- `OperationLog` con `Map<String, Object>`.
- Estructura de paquetes organizada.

## Oportunidades de mejora
- Corregir imports de Lombok: usar `import lombok.Getter;` (no `import.lombok.Getter`).
- Usar `@Getter` y `@Setter` en mayúscula (Java es case-sensitive).
- Agregar enums faltantes: `AccountType`, `LoanType`, `Currency`, `ProductCategory`.
- Cambiar `double` a `BigDecimal` para montos.
- Usar `java.time.LocalDate` en lugar de `java.util.Date`.
- Agregar relación `User → Client`.
- Mejorar README con info de materia, integrantes y cómo ejecutar.
- Agregar tag de entrega y usar formato ADD/CHG en commits.
