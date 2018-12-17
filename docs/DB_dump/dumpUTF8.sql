--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5
-- Dumped by pg_dump version 10.5

-- Started on 2018-12-17 03:18:51 CET

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
-- TOC entry 3232 (class 0 OID 0)
-- Dependencies: 3231
-- Name: DATABASE "ServizioNavetta"; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE "ServizioNavetta" IS 'DataBase per progetto di IGNSW & SIW @unical.it';


--
-- TOC entry 1 (class 3079 OID 13253)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3234 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 17197)
-- Name: Amministratore; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Amministratore" (
    "ID" integer NOT NULL,
    "Nome" "char"[] NOT NULL,
    "Cognome" "char"[] NOT NULL,
    "Password" "char"[] NOT NULL,
    "Email" "char"[] NOT NULL
);


ALTER TABLE public."Amministratore" OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 17213)
-- Name: Autista; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Autista" (
    "ID" integer NOT NULL,
    "Nome" "char"[] NOT NULL,
    "Cognome" "char"[] NOT NULL,
    "Email" "char"[] NOT NULL,
    "Password" "char"[] NOT NULL
);


ALTER TABLE public."Autista" OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17389)
-- Name: Domanda_Riabilitazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Domanda_Riabilitazione" (
    "ID" integer NOT NULL,
    "Data" date NOT NULL,
    "Ora" time with time zone NOT NULL,
    "Studente_ID" integer NOT NULL,
    "Amministatore_ID" integer NOT NULL
);


ALTER TABLE public."Domanda_Riabilitazione" OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 17436)
-- Name: Feedback; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Feedback" (
    "Prenotazione_ID" integer NOT NULL,
    "Contenuto" text NOT NULL
);


ALTER TABLE public."Feedback" OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 17237)
-- Name: Fermata; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Fermata" (
    "Nome" "char"[] NOT NULL,
    "Latitudine" double precision NOT NULL,
    "Longitudine" double precision NOT NULL
);


ALTER TABLE public."Fermata" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 17229)
-- Name: Linea; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Linea" (
    "Nome" "char"[] NOT NULL
);


ALTER TABLE public."Linea" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 17368)
-- Name: Linea_X_Tratto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Linea_X_Tratto" (
    fermata_partenza "char"[] NOT NULL,
    fermata_arrivo "char"[] NOT NULL,
    linea_id "char"[] NOT NULL
);


ALTER TABLE public."Linea_X_Tratto" OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 17245)
-- Name: Navetta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Navetta" (
    "ID" integer NOT NULL,
    "Descrizione" text NOT NULL,
    "Posti_totali" integer NOT NULL
);


ALTER TABLE public."Navetta" OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 17205)
-- Name: Studente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Studente" (
    "Matricola" integer NOT NULL,
    "Flag_attuali" integer NOT NULL,
    "Nome" "char"[] NOT NULL,
    "Cognome" "char"[] NOT NULL,
    "Email" "char"[] NOT NULL,
    "Password" "char"[] NOT NULL
);


ALTER TABLE public."Studente" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 17453)
-- Name: Persona; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public."Persona" AS
 SELECT a."ID",
    a."Nome",
    a."Cognome",
    a."Email",
    a."Password"
   FROM public."Amministratore" a
UNION
 SELECT b."Matricola" AS "ID",
    b."Nome",
    b."Cognome",
    b."Email",
    b."Password"
   FROM public."Studente" b
UNION
 SELECT c."ID",
    c."Nome",
    c."Cognome",
    c."Email",
    c."Password"
   FROM public."Autista" c;


ALTER TABLE public."Persona" OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 17404)
-- Name: Prenotazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Prenotazione" (
    "ID" integer NOT NULL,
    "Giro" integer DEFAULT 0 NOT NULL,
    "Navetta_ID" integer NOT NULL,
    "Obliterato_entrata" boolean DEFAULT false NOT NULL,
    "Obliterato_uscita" boolean DEFAULT false NOT NULL,
    "Tratto::partenza" "char"[] NOT NULL,
    "Tratto::arrivo" "char"[] NOT NULL,
    "Date_time" timestamp with time zone NOT NULL,
    "Autista_ID" integer NOT NULL,
    "Studente_ID" integer NOT NULL
);


ALTER TABLE public."Prenotazione" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 17253)
-- Name: Tratto_di_linea; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Tratto_di_linea" (
    "Fermata_Arrivo" "char"[] NOT NULL,
    "Fermata_Partenza" "char"[] NOT NULL,
    "Tempo_medio_percorrenza_MIN" double precision NOT NULL,
    "Distanza_KM" double precision NOT NULL,
    CONSTRAINT diverso_partenza_arrivo CHECK (("Fermata_Partenza" <> "Fermata_Arrivo"))
);


ALTER TABLE public."Tratto_di_linea" OWNER TO postgres;

--
-- TOC entry 3215 (class 0 OID 17197)
-- Dependencies: 196
-- Data for Name: Amministratore; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Amministratore" ("ID", "Nome", "Cognome", "Password", "Email") FROM stdin;
1	{n}	{c}	{p}	{p,r,o,v,a,@,m,a,i,l,.,s,i}
\.


--
-- TOC entry 3217 (class 0 OID 17213)
-- Dependencies: 198
-- Data for Name: Autista; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Autista" ("ID", "Nome", "Cognome", "Email", "Password") FROM stdin;
2	{a,l,e,s,s,i,o}	{p,o}	{r,t}	{a,r,o}
\.


--
-- TOC entry 3223 (class 0 OID 17389)
-- Dependencies: 204
-- Data for Name: Domanda_Riabilitazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Domanda_Riabilitazione" ("ID", "Data", "Ora", "Studente_ID", "Amministatore_ID") FROM stdin;
\.


--
-- TOC entry 3225 (class 0 OID 17436)
-- Dependencies: 206
-- Data for Name: Feedback; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Feedback" ("Prenotazione_ID", "Contenuto") FROM stdin;
\.


--
-- TOC entry 3219 (class 0 OID 17237)
-- Dependencies: 200
-- Data for Name: Fermata; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Fermata" ("Nome", "Latitudine", "Longitudine") FROM stdin;
\.


--
-- TOC entry 3218 (class 0 OID 17229)
-- Dependencies: 199
-- Data for Name: Linea; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Linea" ("Nome") FROM stdin;
\.


--
-- TOC entry 3222 (class 0 OID 17368)
-- Dependencies: 203
-- Data for Name: Linea_X_Tratto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Linea_X_Tratto" (fermata_partenza, fermata_arrivo, linea_id) FROM stdin;
\.


--
-- TOC entry 3220 (class 0 OID 17245)
-- Dependencies: 201
-- Data for Name: Navetta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Navetta" ("ID", "Descrizione", "Posti_totali") FROM stdin;
\.


--
-- TOC entry 3224 (class 0 OID 17404)
-- Dependencies: 205
-- Data for Name: Prenotazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Prenotazione" ("ID", "Giro", "Navetta_ID", "Obliterato_entrata", "Obliterato_uscita", "Tratto::partenza", "Tratto::arrivo", "Date_time", "Autista_ID", "Studente_ID") FROM stdin;
\.


--
-- TOC entry 3216 (class 0 OID 17205)
-- Dependencies: 197
-- Data for Name: Studente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Studente" ("Matricola", "Flag_attuali", "Nome", "Cognome", "Email", "Password") FROM stdin;
123456	5	{m,i,m,m,o}	{f,l}	{p,r,o,v,a,@,e,x,a,m,p,l,e,.,d,o,m}	{g,a,n,g}
\.


--
-- TOC entry 3221 (class 0 OID 17253)
-- Dependencies: 202
-- Data for Name: Tratto_di_linea; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Tratto_di_linea" ("Fermata_Arrivo", "Fermata_Partenza", "Tempo_medio_percorrenza_MIN", "Distanza_KM") FROM stdin;
\.


--
-- TOC entry 3055 (class 2606 OID 17204)
-- Name: Amministratore Amministratore_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Amministratore"
    ADD CONSTRAINT "Amministratore_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 3059 (class 2606 OID 17220)
-- Name: Autista Autista_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Autista"
    ADD CONSTRAINT "Autista_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 3076 (class 2606 OID 17393)
-- Name: Domanda_Riabilitazione Domanda_Riabilitazione_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Domanda_Riabilitazione"
    ADD CONSTRAINT "Domanda_Riabilitazione_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 3081 (class 2606 OID 17443)
-- Name: Feedback Feedback_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Feedback"
    ADD CONSTRAINT "Feedback_pkey" PRIMARY KEY ("Prenotazione_ID");


--
-- TOC entry 3063 (class 2606 OID 17244)
-- Name: Fermata Fermata_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Fermata"
    ADD CONSTRAINT "Fermata_pkey" PRIMARY KEY ("Nome");


--
-- TOC entry 3061 (class 2606 OID 17236)
-- Name: Linea Linea_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Linea"
    ADD CONSTRAINT "Linea_pkey" PRIMARY KEY ("Nome");


--
-- TOC entry 3065 (class 2606 OID 17252)
-- Name: Navetta Navetta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Navetta"
    ADD CONSTRAINT "Navetta_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 3078 (class 2606 OID 17411)
-- Name: Prenotazione Prenotazione_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Prenotazione"
    ADD CONSTRAINT "Prenotazione_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 3057 (class 2606 OID 17212)
-- Name: Studente Studente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Studente"
    ADD CONSTRAINT "Studente_pkey" PRIMARY KEY ("Matricola");


--
-- TOC entry 3067 (class 2606 OID 17260)
-- Name: Tratto_di_linea Tratto_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Tratto_di_linea"
    ADD CONSTRAINT "Tratto_key" PRIMARY KEY ("Fermata_Arrivo", "Fermata_Partenza");


--
-- TOC entry 3069 (class 2606 OID 17288)
-- Name: Tratto_di_linea Tratto_unique_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Tratto_di_linea"
    ADD CONSTRAINT "Tratto_unique_key" UNIQUE ("Fermata_Arrivo", "Fermata_Partenza");


--
-- TOC entry 3072 (class 2606 OID 17375)
-- Name: Linea_X_Tratto linea_tratto_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Linea_X_Tratto"
    ADD CONSTRAINT linea_tratto_key PRIMARY KEY (fermata_partenza, fermata_arrivo, linea_id);


--
-- TOC entry 3074 (class 2606 OID 17377)
-- Name: Linea_X_Tratto linea_tratto_unique_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Linea_X_Tratto"
    ADD CONSTRAINT linea_tratto_unique_key UNIQUE (fermata_partenza, fermata_arrivo, linea_id);


--
-- TOC entry 3079 (class 1259 OID 17435)
-- Name: fki_foreign_studente; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_foreign_studente ON public."Prenotazione" USING btree ("Studente_ID");


--
-- TOC entry 3070 (class 1259 OID 17388)
-- Name: fki_foreign_tratto; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_foreign_tratto ON public."Linea_X_Tratto" USING btree (fermata_partenza, fermata_arrivo);


--
-- TOC entry 3087 (class 2606 OID 17399)
-- Name: Domanda_Riabilitazione amministratore_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Domanda_Riabilitazione"
    ADD CONSTRAINT amministratore_foreign FOREIGN KEY ("Amministatore_ID") REFERENCES public."Amministratore"("ID");


--
-- TOC entry 3083 (class 2606 OID 17334)
-- Name: Tratto_di_linea arrivo_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Tratto_di_linea"
    ADD CONSTRAINT arrivo_foreign FOREIGN KEY ("Fermata_Arrivo") REFERENCES public."Fermata"("Nome");


--
-- TOC entry 3090 (class 2606 OID 17422)
-- Name: Prenotazione foreign_autista; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Prenotazione"
    ADD CONSTRAINT foreign_autista FOREIGN KEY ("Autista_ID") REFERENCES public."Autista"("ID");


--
-- TOC entry 3084 (class 2606 OID 17378)
-- Name: Linea_X_Tratto foreign_linea; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Linea_X_Tratto"
    ADD CONSTRAINT foreign_linea FOREIGN KEY (linea_id) REFERENCES public."Linea"("Nome");


--
-- TOC entry 3088 (class 2606 OID 17412)
-- Name: Prenotazione foreign_navetta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Prenotazione"
    ADD CONSTRAINT foreign_navetta FOREIGN KEY ("Navetta_ID") REFERENCES public."Navetta"("ID");


--
-- TOC entry 3092 (class 2606 OID 17444)
-- Name: Feedback foreign_prenotazione; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Feedback"
    ADD CONSTRAINT foreign_prenotazione FOREIGN KEY ("Prenotazione_ID") REFERENCES public."Prenotazione"("ID");


--
-- TOC entry 3091 (class 2606 OID 17430)
-- Name: Prenotazione foreign_studente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Prenotazione"
    ADD CONSTRAINT foreign_studente FOREIGN KEY ("Studente_ID") REFERENCES public."Studente"("Matricola");


--
-- TOC entry 3085 (class 2606 OID 17383)
-- Name: Linea_X_Tratto foreign_tratto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Linea_X_Tratto"
    ADD CONSTRAINT foreign_tratto FOREIGN KEY (fermata_partenza, fermata_arrivo) REFERENCES public."Tratto_di_linea"("Fermata_Partenza", "Fermata_Arrivo") MATCH FULL;


--
-- TOC entry 3089 (class 2606 OID 17417)
-- Name: Prenotazione foreign_tratto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Prenotazione"
    ADD CONSTRAINT foreign_tratto FOREIGN KEY ("Tratto::partenza", "Tratto::arrivo") REFERENCES public."Tratto_di_linea"("Fermata_Partenza", "Fermata_Arrivo") MATCH FULL;


--
-- TOC entry 3082 (class 2606 OID 17329)
-- Name: Tratto_di_linea partenza_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Tratto_di_linea"
    ADD CONSTRAINT partenza_foreign FOREIGN KEY ("Fermata_Partenza") REFERENCES public."Fermata"("Nome");


--
-- TOC entry 3086 (class 2606 OID 17394)
-- Name: Domanda_Riabilitazione studente_foreign; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Domanda_Riabilitazione"
    ADD CONSTRAINT studente_foreign FOREIGN KEY ("Studente_ID") REFERENCES public."Studente"("Matricola");


-- Completed on 2018-12-17 03:18:51 CET

--
-- PostgreSQL database dump complete
--

