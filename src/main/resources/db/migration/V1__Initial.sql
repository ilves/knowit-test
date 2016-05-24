SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2979 (class 1262 OID 204654)
-- Dependencies: 2978
-- Name: knowit_challenge; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON DATABASE knowit_challenge IS 'Knowit challenge database';


--
-- TOC entry 7 (class 2615 OID 204655)
-- Name: knowit; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA knowit;


--
-- TOC entry 1 (class 3079 OID 12809)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = knowit, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 178 (class 1259 OID 204680)
-- Name: grade; Type: TABLE; Schema: knowit; Owner: -; Tablespace:
--

CREATE TABLE grade (
    id integer NOT NULL,
    student_id integer,
    subject_id integer,
    grade integer,
    weight integer
);


--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 178
-- Name: TABLE grade; Type: COMMENT; Schema: knowit; Owner: -
--

COMMENT ON TABLE grade IS 'Grades connected to students and subjects';


--
-- TOC entry 177 (class 1259 OID 204678)
-- Name: grade_id_seq; Type: SEQUENCE; Schema: knowit; Owner: -
--

CREATE SEQUENCE grade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 177
-- Name: grade_id_seq; Type: SEQUENCE OWNED BY; Schema: knowit; Owner: -
--

ALTER SEQUENCE grade_id_seq OWNED BY grade.id;


--
-- TOC entry 174 (class 1259 OID 204658)
-- Name: student; Type: TABLE; Schema: knowit; Owner: -; Tablespace:
--

CREATE TABLE student (
    id integer NOT NULL,
    first_name character varying(100),
    last_name character varying
);


--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 174
-- Name: TABLE student; Type: COMMENT; Schema: knowit; Owner: -
--

COMMENT ON TABLE student IS 'Students';


--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN student.first_name; Type: COMMENT; Schema: knowit; Owner: -
--

COMMENT ON COLUMN student.first_name IS 'First name';


--
-- TOC entry 173 (class 1259 OID 204656)
-- Name: student_id_seq; Type: SEQUENCE; Schema: knowit; Owner: -
--

CREATE SEQUENCE student_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 173
-- Name: student_id_seq; Type: SEQUENCE OWNED BY; Schema: knowit; Owner: -
--

ALTER SEQUENCE student_id_seq OWNED BY student.id;


--
-- TOC entry 176 (class 1259 OID 204670)
-- Name: subject; Type: TABLE; Schema: knowit; Owner: -; Tablespace:
--

CREATE TABLE subject (
    id integer NOT NULL,
    name character varying(100)
);


--
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 176
-- Name: TABLE subject; Type: COMMENT; Schema: knowit; Owner: -
--

COMMENT ON TABLE subject IS 'Subjects';


--
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN subject.name; Type: COMMENT; Schema: knowit; Owner: -
--

COMMENT ON COLUMN subject.name IS 'Subject name';


--
-- TOC entry 175 (class 1259 OID 204668)
-- Name: subject_id_seq; Type: SEQUENCE; Schema: knowit; Owner: -
--

CREATE SEQUENCE subject_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 175
-- Name: subject_id_seq; Type: SEQUENCE OWNED BY; Schema: knowit; Owner: -
--

ALTER SEQUENCE subject_id_seq OWNED BY subject.id;


--
-- TOC entry 2848 (class 2604 OID 204683)
-- Name: id; Type: DEFAULT; Schema: knowit; Owner: -
--

ALTER TABLE ONLY grade ALTER COLUMN id SET DEFAULT nextval('grade_id_seq'::regclass);


--
-- TOC entry 2846 (class 2604 OID 204661)
-- Name: id; Type: DEFAULT; Schema: knowit; Owner: -
--

ALTER TABLE ONLY student ALTER COLUMN id SET DEFAULT nextval('student_id_seq'::regclass);


--
-- TOC entry 2847 (class 2604 OID 204673)
-- Name: id; Type: DEFAULT; Schema: knowit; Owner: -
--

ALTER TABLE ONLY subject ALTER COLUMN id SET DEFAULT nextval('subject_id_seq'::regclass);



--
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 177
-- Name: grade_id_seq; Type: SEQUENCE SET; Schema: knowit; Owner: -
--

SELECT pg_catalog.setval('grade_id_seq', 1, true);

--
-- TOC entry 2973 (class 0 OID 204680)
-- Dependencies: 178
-- Data for Name: grade; Type: TABLE DATA; Schema: knowit; Owner: -
--

INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (1, 1, 1, 5, 3);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 1, 2, 5, 3);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 1, 3, 5, 3);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 1, 4, 5, 3);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 1, 5, 5, 3);

INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 2, 1, 5, 3);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 2, 2, 4, 3);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 2, 3, 5, 1);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 2, 4, 5, 5);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 2, 5, 4, 2);

INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 3, 1, 1, 3);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 3, 2, 1, 3);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 3, 3, 1, 2);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 3, 4, 1, 2);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 3, 5, 1, 3);

INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 4, 1, 5, 1);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 4, 2, 2, 1);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 4, 3, 2, 3);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 4, 4, 4, 5);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 4, 5, 4, 5);

INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 5, 1, 5, 2);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 5, 2, 5, 4);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 5, 3, 3, 2);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 5, 4, 5, 3);
INSERT INTO grade (id, student_id, subject_id, grade, weight) VALUES (nextval('grade_id_seq'), 5, 5, 1, 1);



--
-- TOC entry 2969 (class 0 OID 204658)
-- Dependencies: 174
-- Data for Name: student; Type: TABLE DATA; Schema: knowit; Owner: -
--
--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 173
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: knowit; Owner: -
--

SELECT pg_catalog.setval('student_id_seq', 1, true);

INSERT INTO student (id, first_name, last_name) VALUES (1, 'John', 'Smith');
INSERT INTO student (id, first_name, last_name) VALUES (nextval('student_id_seq'), 'Mart', 'Tamm');
INSERT INTO student (id, first_name, last_name) VALUES (nextval('student_id_seq'), 'Mart', 'Maasika');
INSERT INTO student (id, first_name, last_name) VALUES (nextval('student_id_seq'), 'Martin', 'Mürk');
INSERT INTO student (id, first_name, last_name) VALUES (nextval('student_id_seq'), 'Kurt', 'Tumm');





--
-- TOC entry 2971 (class 0 OID 204670)
-- Dependencies: 176
-- Data for Name: subject; Type: TABLE DATA; Schema: knowit; Owner: -
--



--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 175
-- Name: subject_id_seq; Type: SEQUENCE SET; Schema: knowit; Owner: -
--

SELECT pg_catalog.setval('subject_id_seq', 1, true);


INSERT INTO subject (id, name) VALUES (1, 'English');
INSERT INTO subject (id, name) VALUES (nextval('subject_id_seq'), 'Math');
INSERT INTO subject (id, name) VALUES (nextval('subject_id_seq'), 'Biology');
INSERT INTO subject (id, name) VALUES (nextval('subject_id_seq'), 'Physics');
INSERT INTO subject (id, name) VALUES (nextval('subject_id_seq'), 'Linear Algeba');

--
-- TOC entry 2856 (class 2606 OID 204685)
-- Name: grade_pk; Type: CONSTRAINT; Schema: knowit; Owner: -; Tablespace:
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT grade_pk PRIMARY KEY (id);


--
-- TOC entry 2850 (class 2606 OID 204663)
-- Name: student_pk; Type: CONSTRAINT; Schema: knowit; Owner: -; Tablespace:
--

ALTER TABLE ONLY student
    ADD CONSTRAINT student_pk PRIMARY KEY (id);


--
-- TOC entry 2852 (class 2606 OID 204677)
-- Name: subject_name_uq; Type: CONSTRAINT; Schema: knowit; Owner: -; Tablespace:
--

ALTER TABLE ONLY subject
    ADD CONSTRAINT subject_name_uq UNIQUE (name);


--
-- TOC entry 2854 (class 2606 OID 204675)
-- Name: subject_pk; Type: CONSTRAINT; Schema: knowit; Owner: -; Tablespace:
--

ALTER TABLE ONLY subject
    ADD CONSTRAINT subject_pk PRIMARY KEY (id);


--
-- TOC entry 2857 (class 2606 OID 204686)
-- Name: student_grade_fk; Type: FK CONSTRAINT; Schema: knowit; Owner: -
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT student_grade_fk FOREIGN KEY (student_id) REFERENCES student(id);


--
-- TOC entry 2858 (class 2606 OID 204691)
-- Name: subject_grade_fk; Type: FK CONSTRAINT; Schema: knowit; Owner: -
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT subject_grade_fk FOREIGN KEY (subject_id) REFERENCES subject(id);


-- Completed on 2016-03-15 16:23:35 EET

--
-- PostgreSQL database dump complete
--

