## OOP_Exam

### Tables

#### dots
```sql
CREATE TABLE dots
(
    id SERIAL PRIMARY KEY,
    x FLOAT NOT NULL,
    y FLOAT NOT NULL
);
```

#### triangles
```sql
CREATE TABLE triangles
(
    id SERIAL PRIMARY KEY,
    a INTEGER REFERENCES dots(id),
    b INTEGER REFERENCES dots(id),
    c INTEGER REFERENCES dots(id)
);
```
