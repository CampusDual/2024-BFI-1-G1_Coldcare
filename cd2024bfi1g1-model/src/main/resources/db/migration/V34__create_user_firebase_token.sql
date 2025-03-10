CREATE TABLE public.usr_firebase_token (
   uft_id serial NOT NULL PRIMARY KEY,
   usr_id int4 NOT NULL ,
   uft_token varchar(255) NOT NULL
);

ALTER TABLE public.usr_firebase_token ADD CONSTRAINT user_firebase_token_usr_user_fk FOREIGN KEY (usr_id) REFERENCES public.usr_user(usr_id);