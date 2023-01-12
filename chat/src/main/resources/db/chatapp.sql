CREATE TABLE "users" (
  "id" SERIAL PRIMARY KEY,
  "first_name" VARCHAR(255) NOT NULL,
  "second_name" VARCHAR(255) NOT NULL,
  "email" VARCHAR(255) NOT NULL,
  "password" VARCHAR(255) NOT NULL
);

CREATE TABLE "conversations" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(255) NOT NULL,
  "contact_id" INTEGER
);

CREATE TABLE "messages" (
  "id" SERIAL PRIMARY KEY,
  "sender_id" INTEGER,
  "conversation_id" INTEGER,
  "text" TEXT NOT NULL,
  "created_at" DATE
);

CREATE TABLE "contacts" (
  "id" SERIAL PRIMARY KEY,
  "owner_id" INTEGER,
  "user_id" INTEGER
);

ALTER TABLE "messages" ADD FOREIGN KEY ("sender_id") REFERENCES "users" ("id") ON DELETE CASCADE;

ALTER TABLE "messages" ADD FOREIGN KEY ("conversation_id") REFERENCES "conversations" ("id") ON DELETE CASCADE;

ALTER TABLE "conversations" ADD FOREIGN KEY ("contact_id") REFERENCES "contacts" ("id") ON DELETE CASCADE;

ALTER TABLE "contacts" ADD FOREIGN KEY ("owner_id") REFERENCES "users" ("id") ON DELETE CASCADE;
