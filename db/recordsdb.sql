--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE cmsc495;
ALTER ROLE cmsc495 WITH SUPERUSER INHERIT NOCREATEROLE NOCREATEDB LOGIN NOREPLICATION NOBYPASSRLS PASSWORD 'md5708533fb99c99657baa442789fbbcfb7';
CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;






\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.0 (Debian 11.0-1.pgdg90+2)
-- Dumped by pg_dump version 11.0 (Debian 11.0-1.pgdg90+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- PostgreSQL database dump complete
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.0 (Debian 11.0-1.pgdg90+2)
-- Dumped by pg_dump version 11.0 (Debian 11.0-1.pgdg90+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 11.0 (Debian 11.0-1.pgdg90+2)
-- Dumped by pg_dump version 11.0 (Debian 11.0-1.pgdg90+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: recordsdb; Type: DATABASE; Schema: -; Owner: cmsc495
--

CREATE DATABASE recordsdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE recordsdb OWNER TO cmsc495;

\connect recordsdb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: userlogs; Type: TABLE; Schema: public; Owner: cmsc495
--

CREATE TABLE public.userlogs (
    id integer NOT NULL,
    username text,
    "timestamp" text
);


ALTER TABLE public.userlogs OWNER TO cmsc495;

--
-- Name: userlogs_id_seq; Type: SEQUENCE; Schema: public; Owner: cmsc495
--

CREATE SEQUENCE public.userlogs_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.userlogs_id_seq OWNER TO cmsc495;

--
-- Name: userlogs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cmsc495
--

ALTER SEQUENCE public.userlogs_id_seq OWNED BY public.userlogs.id;


--
-- Name: userlogs id; Type: DEFAULT; Schema: public; Owner: cmsc495
--

ALTER TABLE ONLY public.userlogs ALTER COLUMN id SET DEFAULT nextval('public.userlogs_id_seq'::regclass);


--
-- Data for Name: userlogs; Type: TABLE DATA; Schema: public; Owner: cmsc495
--

COPY public.userlogs (id, username, "timestamp") FROM stdin;
\.


--
-- Name: userlogs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: cmsc495
--

SELECT pg_catalog.setval('public.userlogs_id_seq', 1, false);


--
-- Name: userlogs userlogs_pkey; Type: CONSTRAINT; Schema: public; Owner: cmsc495
--

ALTER TABLE ONLY public.userlogs
    ADD CONSTRAINT userlogs_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

