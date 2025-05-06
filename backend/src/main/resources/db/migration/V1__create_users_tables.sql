create table if not exists users(
    id int not null auto_increment,
    name varchar(20) not null,
    photo varchar(200) ,
    birthday date,
    email varchar(30) not null,
    created_at datetime default current_timestamp not null,
    updated_at datetime default current_timestamp on update current_timestamp,
    deleted_at datetime default NULL,
    primary key(id)
);

create table if not exists goals(
    id int not null auto_increment,
    user_id int not null,
    title varchar(30) not null,
    content varchar(150) not null,
    date date not null,
    created_at datetime default current_timestamp not null,
    updated_at datetime default current_timestamp on update current_timestamp,
    deleted_at datetime default NULL,
    primary key(id),
    foreign key(user_id)references users(id)
);

create table if not exists milestone(
    id int not null auto_increment,
    goal_id int not null,
    title varchar(30) not null,
    content varchar(150) not null,
    date date not null,
    created_at datetime default current_timestamp not null,
    updated_at datetime default current_timestamp on update current_timestamp,
    deleted_at datetime default NULL,
    primary key(id),
    foreign key(goal_id)references goals(id)
);