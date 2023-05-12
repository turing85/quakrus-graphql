CREATE SCHEMA v1;

CREATE VIEW v1.authors AS
    SELECT id, name, surname
    FROM public.authors;

CREATE VIEW v1.books AS
    SELECT id, name, isin
    FROM public.books;

CREATE VIEW v1.tags AS
    SELECT id, name
    FROM public.tags;

CREATE VIEW v1.authors_to_books AS
    SELECT fk_author, fk_book
    FROM public.authors_to_books;

CREATE VIEW v1.tags_to_books AS
    SELECT fk_tag, fk_book
    FROM public.tags_to_books;

GRANT USAGE ON SCHEMA v1 TO graphql GRANTED BY current_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA v1 TO graphql GRANTED BY current_user;

GRANT USAGE ON SEQUENCE authors__seq__id TO graphql GRANTED BY current_user;
GRANT USAGE ON SEQUENCE books__seq__id TO graphql GRANTED BY current_user;
GRANT USAGE ON SEQUENCE tags__seq__id TO graphql GRANTED BY current_user;