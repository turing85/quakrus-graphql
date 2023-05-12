-- TABLE authors
CREATE SEQUENCE authors__seq__id AS BIGINT START WITH 1 INCREMENT BY 1;

CREATE TABLE public.authors (
   id BIGINT CONSTRAINT authors__pk__id PRIMARY KEY DEFAULT nextval('authors__seq__id'),
   name VARCHAR(127) CONSTRAINT authors__not_null__name NOT NULL,
   surname VARCHAR(127) CONSTRAINT authors__not_null__surnamename NOT NULL
);

ALTER SEQUENCE authors__seq__id OWNED BY public.authors.id;

-- TABLE books
CREATE SEQUENCE books__seq__id AS BIGINT START WITH 1 INCREMENT BY 1;

CREATE TABLE public.books (
    id BIGINT CONSTRAINT books__pk__id PRIMARY KEY DEFAULT nextval('books__seq__id'),
    name VARCHAR(127) CONSTRAINT books__not_null__name NOT NULL,
    isin VARCHAR(17) CONSTRAINT books__not_null__isin NOT NULL,
    CONSTRAINT books__unique__isin UNIQUE(isin)
);

ALTER SEQUENCE books__seq__id OWNED BY public.books.id;

-- TABLE tags
CREATE SEQUENCE tags__seq__id AS BIGINT START WITH 1 INCREMENT BY 1;

CREATE TABLE public.tags (
    id BIGINT CONSTRAINT tag__pk__id PRIMARY KEY DEFAULT nextval('tags__seq__id'),
    name VARCHAR(63) CONSTRAINT tags__not_null__name NOT NULL UNIQUE
);

ALTER SEQUENCE tags__seq__id OWNED BY public.tags.id;

-- JOIN TABLE authors_to_books
CREATE TABLE public.authors_to_books (
    fk_author BIGINT REFERENCES public.authors(id) CONSTRAINT authors_to_books__not_null_author NOT NULL,
    fk_book BIGINT REFERENCES  public.books(id) CONSTRAINT authors_to_books__not_null_book NOT NULL,
    CONSTRAINT authors_to_books__pk__fk_author_fk_book PRIMARY KEY (fk_author, fk_book)
);

-- JOIN TABLE tags_to_books
CREATE TABLE public.tags_to_books (
    fk_tag BIGINT REFERENCES public.tags(id) CONSTRAINT tags_to_books__not_null_tag NOT NULL,
    fk_book BIGINT REFERENCES  public.books(id) CONSTRAINT tags_to_books__not_null_book NOT NULL,
    CONSTRAINT  tags_to_books__pk__fk_tag_fk_book PRIMARY KEY (fk_tag, fk_book)
);