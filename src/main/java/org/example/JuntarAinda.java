package org.example;

//public class Main {
  //  public static void main(String[] args) {
        /*
        ALTERAÇÕES DO BANCO DE DADOS COMO COLUNAS E O TRIGGER

        CREATE TABLE prontuario (
                id SERIAL PRIMARY KEY,
                nome TEXT,
                data DATE,
                motivo TEXT,
                prescricao TEXT,
                observacao TEXT
        );

        ALTER TABLE consulta
        DROP COLUMN consulta_id;

        ALTER TABLE prontuario
        DROP COLUMN consulta_id;

        CREATE OR REPLACE FUNCTION inserir_prontuario()
        RETURNS TRIGGER AS $$
        BEGIN
        INSERT INTO prontuario (
                nome, data, motivo, prescricao, observacao
        )
        VALUES (
                NEW.nome, NEW.data, NEW.motivo, NEW.prescricao, NEW.observacao
        );

        RETURN NEW;
        END;
        $$ LANGUAGE plpgsql;


        CREATE TRIGGER trigger_inserir_prontuario
        AFTER INSERT ON consulta
        FOR EACH ROW
        EXECUTE FUNCTION inserir_prontuario();
        */
  //  }
//}