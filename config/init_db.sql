-- auto-generated definition
create table resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text
);

comment on table resume is 'Resume Table';

alter table resume
    owner to postgres;

-- auto-generated definition
create table contact
(
    id          serial   not null
        constraint contact_pk
            primary key,
    type        text     not null,
    value       text     not null,
    resume_uuid char(36) not null
        constraint contact_resume_uuid_fk
            references resume
            on delete cascade
);

alter table contact
    owner to postgres;

-- auto-generated definition
create unique index contact_uuid_type_index
    on contact (resume_uuid, type);

