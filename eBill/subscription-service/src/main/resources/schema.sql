drop SCHEMA if exists customer;
CREATE SCHEMA customer;
ALTER SCHEMA customer OWNER TO dlpc_customer_user01;

CREATE TABLE customer.account (
    account_id character varying(255) NOT NULL,
    name character varying(255)
);

ALTER TABLE customer.account OWNER TO dlpc_customer_user01;

CREATE TABLE customer.contact (
    id integer NOT NULL,
    prim_sw character(1) NOT NULL,
    stat_flg character varying(255) NOT NULL,
    value character varying(255),
    account_id character varying(255) NOT NULL,
    contact_type character varying(255) NOT NULL
);

ALTER TABLE customer.contact OWNER TO dlpc_customer_user01;

CREATE SEQUENCE customer.contact_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER TABLE customer.contact_id_seq OWNER TO dlpc_customer_user01;	
ALTER SEQUENCE customer.contact_id_seq OWNED BY customer.contact.id;

CREATE TABLE customer.contact_type (
    type_code character varying(255) NOT NULL,
    description character varying(255)
);

ALTER TABLE customer.contact_type OWNER TO dlpc_customer_user01;

CREATE TABLE customer.subscription (
    id integer NOT NULL,
    stat_flg character varying(255) NOT NULL,
    subscribe character(1),
    account_id character varying(255) NOT NULL,
    subscription_type character varying(255) NOT NULL
);

ALTER TABLE customer.subscription OWNER TO dlpc_customer_user01;

CREATE SEQUENCE customer.subscription_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE customer.subscription_id_seq OWNER TO dlpc_customer_user01;
ALTER SEQUENCE customer.subscription_id_seq OWNED BY customer.subscription.id;

CREATE TABLE customer.subscription_type (
    type_code character varying(255) NOT NULL,
    description character varying(255)
);

ALTER TABLE customer.subscription_type OWNER TO dlpc_customer_user01;
ALTER TABLE ONLY customer.contact ALTER COLUMN id SET DEFAULT nextval('customer.contact_id_seq'::regclass);
ALTER TABLE ONLY customer.subscription ALTER COLUMN id SET DEFAULT nextval('customer.subscription_id_seq'::regclass);


CREATE UNIQUE INDEX account_contact_UNIQUE
ON customer.contact (account_id, contact_type, value);

CREATE UNIQUE INDEX account_subscription_UNIQUE
ON customer.subscription (account_id, subscription_type);