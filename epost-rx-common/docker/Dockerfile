FROM mcr.microsoft.com/mssql/server:2019-latest

USER root

RUN mkdir /app

COPY ./ /app

WORKDIR /app

cmd ["/app/entrypoint.sh"]


