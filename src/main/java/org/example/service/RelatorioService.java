package org.example.service;

import org.example.repository.*;

import java.util.Scanner;

public abstract class RelatorioService {


    protected PacienteRepository pacienteRepository;
    protected MedicoRepository medicoRepository;
    protected ProdutoRepository estoqueRepository;
    protected UsuarioRepository usuarioRepository;
    //protected ExameRepository exameRepository;
    protected ConsultaRepository consultaRepository;

    public RelatorioService() {
    }

    public abstract void gerarRelatorio();

}
