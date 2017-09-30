DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 110000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                  NOT NULL,
  email            VARCHAR                  NOT NULL,
  password         VARCHAR                  NOT NULL,
  registered       TIMESTAMP DEFAULT now()  NOT NULL,
  enabled          BOOL DEFAULT TRUE        NOT NULL,
  calories_per_day INTEGER DEFAULT 2000     NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

/* 1/КОММЕНТЫ КАК В JAVE

2.Ускорение поиска users_unique_email_idx. Допустим команда указывающая не искать циклом после первого найденного.
т.к он уникальный*/

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE products (
 -- id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  --глобал сиквенс не обновлялся поэтому
  id          INTEGER DEFAULT nextval('global_seq'),
  user_id     INTEGER   NOT NULL,
  --ПО ТЕКСТОВОМУ СКРИПТУ ХАРД МЕМОРИ ТРЭЙН--потом просто убрать из таблицы.
  --можно тоже скриптом, как для трени.
  --ПРОГА ФАЙЛОВОГО СКРИПТА НА JAVA УБИРАЮЩАЯ ДЕСКРИПЦИЮ И КАЛОРИИ, А ДЭЙТ ТАЙМ В КОНЕЦ.
  -- И ВТОРОЙ ДЭЙТ ТАЙМ ВРЕМЕНИ АПДЭЙТА. КОТОРЫЙ БУДЕТ ОБНОВЛЯЦА В РЕАЛЬНОМ ВРЕМЕНИ
  date_time   TIMESTAMP NOT NULL,
  description TEXT      NOT NULL,
  calories    INT       NOT NULL,
  type1       TEXT      DEFAULT 'o',
  type2       TEXT      DEFAULT 'o',
  --КОД 500 чтобы не баговалось по фотке
  cod         INT       DEFAULT 0,
  naimenovanie   TEXT   DEFAULT 'o',
  proizvoditel   TEXT   DEFAULT 'o',
  edizmereniya   TEXT   DEFAULT 'o',
  kolvo       INT       DEFAULT 0,
  cena        INT       DEFAULT 0,
  primechanie TEXT      DEFAULT 'o',
  articul     TEXT     DEFAULT 'o',
  picture     TEXT      DEFAULT 'o',



  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX meals_unique_user_datetime_idx ON products (user_id, date_time)