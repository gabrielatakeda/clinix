package org.example.service;

public abstract class RelatorioService {


    protected PacienteRepository pacienteRepository;
    protected MedicoRepository medicoRepository;
    protected EstoqueRepository estoqueRepository;
    protected UsuarioRepository usuarioRepository;
    protected ExameRepository exameRepository;
    protected ConsultaRepository consultaRepository;

    public RelatorioService() {
    }

    public abstract void gerarRelatorio();


}
