CREATE TABLE usuarios (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    roles TEXT NOT NULL
);