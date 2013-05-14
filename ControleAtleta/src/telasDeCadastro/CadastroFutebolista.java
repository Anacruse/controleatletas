package controleatleta;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CadastroFutebolista extends javax.swing.JFrame {

    private final byte FUNCAO_TATICA_GOLEIRO_INDICE = 0;
    private final byte FUNCAO_TATICA_DEFESA_INDICE = 1;
    private final byte FUNCAO_TATICA_MEIO_CAMPO_INDICE = 2;
    private final byte FUNCAO_TATICA_ATAQUE_INDICE = 3;
    private final char FUNCAO_TATICA_GOLEIRO_VALOR = 'G';
    private final char FUNCAO_TATICA_DEFESA_VALOR = 'D';
    private final char FUNCAO_TATICA_MEIO_CAMPO_VALOR = 'M';
    private final char FUNCAO_TATICA_ATAQUE_VALOR = 'A';
    private final byte SEXO_MASCULINO_INDICE = 0;
    private final byte SEXO_FEMININO_INDICE = 1;
    private final char SEXO_MASCULINO_VALOR = 'M';
    private final char SEXO_FEMININO_VALOR = 'F';
    private final byte CATEGORIA_AMADOR_INDICE = 0;
    private final byte CATEGORIA_PROFISSIONAL_INDICE = 1;
    private final char CATEGORIA_AMADOR_VALOR = 'A';
    private final char CATEGORIA_PROFISSIONAL_VALOR = 'P';
    private final byte NAO_E_CAPITAO_INDICE = 0;
    private final byte E_CAPITAO_INDICE = 1;
    private final char NAO_E_CAPITAO_VALOR = 'N';
    private final char E_CAPITAO_VALOR = 'S';
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private ControleFutebolista controleFutebolista;
    private Futebolista umFutebolista;
    private boolean modoAlteracao;
    private boolean novoRegistro;
    private DefaultListModel telefonesListModel;
    private DefaultListModel patrocinioListModel;

    public CadastroFutebolista() {
        initComponents();
        this.habilitarDesabilitarCampos();
        this.controleFutebolista = new ControleFutebolista();
        this.telefonesListModel = new DefaultListModel();
        this.jListTelefones.setModel(telefonesListModel);
        this.patrocinioListModel = new DefaultListModel();
        this.jListPatrocinios.setModel(patrocinioListModel);
        this.jTableListaFutebolistas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void limparCampos() {
        jTextFieldAltura.setText("0.0");
        jTextFieldBairro.setText(null);
        jTextFieldCep.setText(null);
        jTextFieldCidade.setText(null);
        jTextFieldComplemento.setText(null);
        jTextFieldCpf.setText(null);
        jTextFieldDataNascimento.setText(null);
        jTextFieldTime.setText(null);
        jComboBoxEstado.setSelectedIndex(0);
        jTextFieldLogradouro.setText(null);
        jTextFieldNome.setText(null);
        jTextFieldNomeMae.setText(null);
        jTextFieldNomePai.setText(null);
        jTextFieldNumero.setText("0");
        jTextFieldPais.setText(null);
        jTextFieldPeso.setText("0.0");
        jTextFieldRg.setText(null);
        jComboBoxFuncaoTatica.setSelectedIndex(0);
        jTextFieldTotalCartoesVermelhos.setText("0");
        jTextFieldTotalCartoesAmarelos.setText("0");
        telefonesListModel.clear();
        patrocinioListModel.clear();
        jComboBoxSexo.setSelectedIndex(0);
        jComboBoxCategoria.setSelectedIndex(0);
        jSpinnerNumeroCamisa.setValue(1);
        jComboBoxCapitao.setSelectedIndex(0);
        this.atualizarCategoria();
    }

    private void preencherCampos() {
        
        ArrayList<String> telefones;
        ArrayList<Patrocinio> patrocinios;

        jTextFieldAltura.setText(Double.toString(umFutebolista.getAltura()));
        jTextFieldBairro.setText(umFutebolista.getEndereco().getBairro());
        jTextFieldCep.setText(umFutebolista.getEndereco().getCep());
        jTextFieldCidade.setText(umFutebolista.getEndereco().getCidade());
        jTextFieldComplemento.setText(umFutebolista.getEndereco().getComplemento());
        jTextFieldCpf.setText(umFutebolista.getCpf());
        if (umFutebolista.getDataNascimento() == null) {
            jTextFieldDataNascimento.setText(null);
        } else {
            jTextFieldDataNascimento.setText(dateFormat.format(umFutebolista.getDataNascimento()));
        }
        jComboBoxEstado.setSelectedItem(umFutebolista.getEndereco().getEstado());
        jTextFieldLogradouro.setText(umFutebolista.getEndereco().getLogradouro());
        jTextFieldNome.setText(umFutebolista.getNome());
        jTextFieldNomeMae.setText(umFutebolista.getNomeMae());
        jTextFieldNomePai.setText(umFutebolista.getNomePai());
        jTextFieldNumero.setText(umFutebolista.getEndereco().getNumero().toString());
        jTextFieldPais.setText(umFutebolista.getEndereco().getPais());
        jTextFieldPeso.setText(Double.toString(umFutebolista.getPeso()));
        jTextFieldRg.setText(umFutebolista.getRg());

        telefonesListModel.clear();
        telefones = umFutebolista.getTelefones();
        for (String t : telefones) {
            telefonesListModel.addElement(t);
        }

        patrocinioListModel.clear();
        patrocinios = umFutebolista.getPatrocinios();
        for (Patrocinio p : patrocinios) {
            patrocinioListModel.addElement(p);
        }

        switch (umFutebolista.getSexo()) {
            case SEXO_MASCULINO_VALOR:
                jComboBoxSexo.setSelectedIndex(SEXO_MASCULINO_INDICE);
                break;
            case SEXO_FEMININO_VALOR:
                jComboBoxSexo.setSelectedIndex(SEXO_FEMININO_INDICE);
                break;
        }
        
        switch(umFutebolista.getCapitao()){
            case E_CAPITAO_VALOR:
                jComboBoxCapitao.setSelectedIndex(E_CAPITAO_INDICE);
                break;
            case NAO_E_CAPITAO_VALOR:
                jComboBoxCapitao.setSelectedIndex(NAO_E_CAPITAO_INDICE);
                break;
        }

        switch (umFutebolista.getFuncaoTatica()) {
            case FUNCAO_TATICA_GOLEIRO_VALOR:
                jComboBoxFuncaoTatica.setSelectedIndex(FUNCAO_TATICA_GOLEIRO_INDICE);
                break;
             case FUNCAO_TATICA_DEFESA_VALOR:
                jComboBoxFuncaoTatica.setSelectedIndex(FUNCAO_TATICA_DEFESA_INDICE);
                break;
             case FUNCAO_TATICA_MEIO_CAMPO_VALOR:
                jComboBoxFuncaoTatica.setSelectedIndex(FUNCAO_TATICA_MEIO_CAMPO_INDICE);
                break;
             case FUNCAO_TATICA_ATAQUE_VALOR:
                jComboBoxFuncaoTatica.setSelectedIndex(FUNCAO_TATICA_ATAQUE_INDICE);
                break;
        }
        
        switch (umFutebolista.getCategoria()) {
            case CATEGORIA_AMADOR_VALOR:
                jComboBoxCategoria.setSelectedIndex(CATEGORIA_AMADOR_INDICE);
                break;
            case CATEGORIA_PROFISSIONAL_VALOR:
                jComboBoxCategoria.setSelectedIndex(CATEGORIA_PROFISSIONAL_INDICE);
                break;
        }
        
        jSpinnerNumeroCamisa.setValue(umFutebolista.getNumeroCamisa());
        
        jTextFieldTime.setText(umFutebolista.getTime());
        jTextFieldTotalCartoesAmarelos.setText(String.valueOf(umFutebolista.getCartoesAmarelos()));
        jTextFieldTotalCartoesVermelhos.setText(String.valueOf(umFutebolista.getCartoesVermelhos()));

        this.atualizarCategoria();
    }

    private boolean validarCampos() {
        if (jTextFieldNome.getText().trim().length() == 0) {
            this.exibirInformacao("O valor do campo 'Nome' não foi informado.");
            jTextFieldNome.requestFocus();
            return false;
        }
        if (jTextFieldDataNascimento.getText().length() != 0) {
            try {
                dateFormat.parse(jTextFieldDataNascimento.getText());
            } catch (ParseException ex) {
                this.exibirInformacao("O valor do campo 'Data de Nascimento' é inválido.");
                jTextFieldDataNascimento.requestFocus();
                return false;
            }
        }
        try {
            Double.parseDouble(jTextFieldAltura.getText());
        } catch (Exception ex) {
            this.exibirInformacao("O valor do campo 'Altura' é inválido.");
            jTextFieldAltura.requestFocus();
            return false;
        }
        try {
            Double.parseDouble(jTextFieldPeso.getText());
        } catch (Exception ex) {
            this.exibirInformacao("O valor do campo 'Peso' é inválido.");
            jTextFieldPeso.requestFocus();
            return false;
        }
        if (!jTextFieldNumero.getText().equals("")) {
            try {
                Integer.parseInt(jTextFieldNumero.getText());
            } catch (Exception ex) {
                this.exibirInformacao("O valor do campo 'Número' é inválido.");
                jTextFieldNumero.requestFocus();
                return false;
            }
        }
        /*
        try {
            Double.parseDouble(jTextFieldTime.getText());
        } catch (Exception ex) {
            this.exibirInformacao("O valor do campo 'Envergadura' é inválido.");
            jTextFieldTime.requestFocus();
            return false;
        }
        
        try {
            Integer.parseInt(jTextFieldPosicao.getText());
        } catch (Exception ex) {
            this.exibirInformacao("O valor do campo 'Total de Lutas' é inválido.");
            jTextFieldPosicao.requestFocus();
            return false;
        }
        */
        try {
            Integer.parseInt(jTextFieldTotalCartoesVermelhos.getText());
        } catch (Exception ex) {
            this.exibirInformacao("O valor do campo 'Total de Nocautes' é inválido.");
            jTextFieldTotalCartoesVermelhos.requestFocus();
            return false;
        }
        try {
            Integer.parseInt(jTextFieldTotalCartoesAmarelos.getText());
        } catch (Exception ex) {
            this.exibirInformacao("O valor do campo 'Total de Vitórias' é inválido.");
            jTextFieldTotalCartoesAmarelos.requestFocus();
            return false;
        }
        return true;
    }

    private void habilitarDesabilitarCampos() {
        boolean registroSelecionado = (umFutebolista != null);
        jTextFieldAltura.setEnabled(modoAlteracao);
        jTextFieldBairro.setEnabled(modoAlteracao);
        jTextFieldCep.setEnabled(modoAlteracao);
        jTextFieldCidade.setEnabled(modoAlteracao);
        jTextFieldComplemento.setEnabled(modoAlteracao);
        jTextFieldCpf.setEnabled(modoAlteracao);
        jTextFieldDataNascimento.setEnabled(modoAlteracao);
        jTextFieldTime.setEnabled(modoAlteracao);
        jComboBoxEstado.setEnabled(modoAlteracao);
        jTextFieldLogradouro.setEnabled(modoAlteracao);
        jTextFieldNome.setEnabled(modoAlteracao);
        jTextFieldNomeMae.setEnabled(modoAlteracao);
        jTextFieldNomePai.setEnabled(modoAlteracao);
        jTextFieldNumero.setEnabled(modoAlteracao);
        jTextFieldPais.setEnabled(modoAlteracao);
        jTextFieldPeso.setEnabled(modoAlteracao);
        jTextFieldRg.setEnabled(modoAlteracao);
        jComboBoxFuncaoTatica.setEnabled(modoAlteracao);
        jTextFieldTotalCartoesVermelhos.setEnabled(modoAlteracao);
        jTextFieldTotalCartoesAmarelos.setEnabled(modoAlteracao);
        jButtonNovo.setEnabled(modoAlteracao == false);
        jButtonAlterar.setEnabled(modoAlteracao == false && registroSelecionado == true);
        jButtonExcluir.setEnabled(modoAlteracao == false && registroSelecionado == true);
        jButtonPesquisar.setEnabled(modoAlteracao == false);
        jButtonSalvar.setEnabled(modoAlteracao);
        jButtonCancelar.setEnabled(modoAlteracao);
        jButtonAdicionarTelefone.setEnabled(modoAlteracao);
        jButtonRemoverTelefone.setEnabled(modoAlteracao);
        jButtonAdicionarPatrocinio.setEnabled(modoAlteracao);
        jButtonRemoverPatrocinio.setEnabled(modoAlteracao);
        jComboBoxSexo.setEnabled(modoAlteracao);
        jComboBoxCategoria.setEnabled(modoAlteracao);
        jSpinnerNumeroCamisa.setEnabled(modoAlteracao);
        jTableListaFutebolistas.setEnabled(modoAlteracao == false);
        jComboBoxCapitao.setEnabled(modoAlteracao);

    }

    private void salvarRegistro() {
        Endereco endereco;
        ArrayList<String> telefones;
        ArrayList<Patrocinio> patrocinios;
        Date dataNascimento;

        if (this.validarCampos() == false) {
            return;
        }

        if (jTextFieldDataNascimento.getText().length() == 0) {
            dataNascimento = null;
        } else {
            try {
                dataNascimento = dateFormat.parse(jTextFieldDataNascimento.getText());
            } catch (ParseException ex) {
                exibirInformacao("Falha ao gravar a data de nascimento: " + ex.toString());
                return;
            }
        }

        endereco = new Endereco();
        endereco.setBairro(jTextFieldBairro.getText());
        endereco.setCep(jTextFieldCep.getText());
        endereco.setCidade(jTextFieldCidade.getText());
        endereco.setComplemento(jTextFieldComplemento.getText());
        endereco.setEstado((String) jComboBoxEstado.getSelectedItem());
        endereco.setLogradouro(jTextFieldLogradouro.getText());
        endereco.setNumero(jTextFieldNumero.getText());
        endereco.setPais(jTextFieldPais.getText());

        telefones = new ArrayList<String>();
        for (int i = 0; i < telefonesListModel.size(); i++) {
            telefones.add(telefonesListModel.getElementAt(i).toString());
        }

        patrocinios = new ArrayList<Patrocinio>();
        for (int i = 0; i < patrocinioListModel.size(); i++) {
            Patrocinio premiacao = (Patrocinio) patrocinioListModel.getElementAt(i);
            patrocinios.add(premiacao);
        }

        if (novoRegistro == true) {
            umFutebolista = new Futebolista(jTextFieldNome.getText());
        } else {
            umFutebolista.setNome(jTextFieldNome.getText());
        }
        umFutebolista.setEndereco(endereco);
        umFutebolista.setTelefones(telefones);
        umFutebolista.setPatrocinios(patrocinios);
        umFutebolista.setDataNascimento(dataNascimento);
        umFutebolista.setAltura(Double.parseDouble(jTextFieldAltura.getText()));
        umFutebolista.setNomeMae(jTextFieldNomeMae.getText());
        umFutebolista.setNomePai(jTextFieldNomePai.getText());
        umFutebolista.setPeso(Double.parseDouble(jTextFieldPeso.getText()));
        umFutebolista.setCpf(jTextFieldCpf.getText());
        umFutebolista.setRg(jTextFieldRg.getText());


        switch (jComboBoxSexo.getSelectedIndex()) {
            case SEXO_MASCULINO_INDICE:
                umFutebolista.setSexo(SEXO_MASCULINO_VALOR);
                break;
            case SEXO_FEMININO_INDICE:
                umFutebolista.setSexo(SEXO_FEMININO_VALOR);
                break;
        }

        switch (jComboBoxCategoria.getSelectedIndex()) {
            case CATEGORIA_AMADOR_INDICE:
                umFutebolista.setCategoria(CATEGORIA_AMADOR_VALOR);
                break;
            case CATEGORIA_PROFISSIONAL_INDICE:
                umFutebolista.setCategoria(CATEGORIA_PROFISSIONAL_VALOR);
                break;
        }

       switch (jComboBoxCapitao.getSelectedIndex()) {
            case NAO_E_CAPITAO_INDICE:
                umFutebolista.setCapitao(NAO_E_CAPITAO_VALOR);
                break;
            case E_CAPITAO_INDICE:
                umFutebolista.setCapitao(E_CAPITAO_VALOR);
                break;
        }
       
       switch (jComboBoxFuncaoTatica.getSelectedIndex()) {
            case FUNCAO_TATICA_GOLEIRO_INDICE:
                umFutebolista.setFuncaoTatica(FUNCAO_TATICA_GOLEIRO_VALOR);
                break;
             case FUNCAO_TATICA_DEFESA_INDICE:
                umFutebolista.setFuncaoTatica(FUNCAO_TATICA_DEFESA_VALOR);
                break;
             case FUNCAO_TATICA_MEIO_CAMPO_INDICE:
                umFutebolista.setFuncaoTatica(FUNCAO_TATICA_MEIO_CAMPO_VALOR);
                break;
             case FUNCAO_TATICA_ATAQUE_INDICE:
                umFutebolista.setFuncaoTatica(FUNCAO_TATICA_ATAQUE_VALOR);
                break;
        }
       
       umFutebolista.setTime(jTextFieldTime.getText());
       umFutebolista.setCartoesAmarelos(Integer.parseInt(jTextFieldTotalCartoesAmarelos.getText()));
       umFutebolista.setCartoesVermelhos(Integer.parseInt(jTextFieldTotalCartoesVermelhos.getText()));
       umFutebolista.setNumeroCamisa((Integer)jSpinnerNumeroCamisa.getValue());
       
        if (novoRegistro == true) {
            controleFutebolista.adicionar(umFutebolista);
        }
        modoAlteracao = false;
        novoRegistro = false;
        this.carregarListaFutebolistas();
        this.habilitarDesabilitarCampos();
    }

    private void carregarListaFutebolistas() {
        ArrayList<Futebolista> listaFutebolistas = controleFutebolista.getListaFutebolistas();
        DefaultTableModel model = (DefaultTableModel) jTableListaFutebolistas.getModel();
        model.setRowCount(0);
        for (Futebolista b : listaFutebolistas) {
            model.addRow(new String[]{b.getNome(), b.getCpf()});
        }
        jTableListaFutebolistas.setModel(model);
    }

    private void exibirInformacao(String info) {
        JOptionPane.showMessageDialog(this, info, "Atenção", JOptionPane.INFORMATION_MESSAGE);
    }

    private void atualizarCategoria() {
        char categoria;
        switch (jComboBoxCategoria.getSelectedIndex()) {
            case CATEGORIA_AMADOR_INDICE:
                categoria = CATEGORIA_AMADOR_VALOR;
                break;
            case CATEGORIA_PROFISSIONAL_INDICE:
                categoria = CATEGORIA_PROFISSIONAL_VALOR;
                break;
            default:
                return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonNovo = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelNome = new javax.swing.JLabel();
        jLabelDataNascimento = new javax.swing.JLabel();
        jLabelAltura = new javax.swing.JLabel();
        jTextFieldAltura = new javax.swing.JTextField();
        jTextFieldPeso = new javax.swing.JTextField();
        jLabelPeso = new javax.swing.JLabel();
        jTextFieldNomePai = new javax.swing.JTextField();
        jLabelNomePai = new javax.swing.JLabel();
        jTextFieldNomeMae = new javax.swing.JTextField();
        jLabelNomeMae = new javax.swing.JLabel();
        jLabelSexo = new javax.swing.JLabel();
        jLabelRg = new javax.swing.JLabel();
        jLabelCpf = new javax.swing.JLabel();
        jLabelTelefones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListTelefones = new javax.swing.JList();
        jButtonAdicionarTelefone = new javax.swing.JButton();
        jButtonRemoverTelefone = new javax.swing.JButton();
        jComboBoxSexo = new javax.swing.JComboBox();
        jTextFieldDataNascimento = new javax.swing.JTextField();
        jTextFieldRg = new javax.swing.JTextField();
        jTextFieldCpf = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabelLogradouro = new javax.swing.JLabel();
        jTextFieldLogradouro = new javax.swing.JTextField();
        jLabelNumero = new javax.swing.JLabel();
        jTextFieldNumero = new javax.swing.JTextField();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabelBairro = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jLabelCidade = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelPais = new javax.swing.JLabel();
        jTextFieldPais = new javax.swing.JTextField();
        jLabelComplemento = new javax.swing.JLabel();
        jTextFieldComplemento = new javax.swing.JTextField();
        jLabelCep = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox();
        jTextFieldCep = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabelCategoria = new javax.swing.JLabel();
        jComboBoxCategoria = new javax.swing.JComboBox();
        jLabelCpitao = new javax.swing.JLabel();
        jLabelNumeroDaCamisa = new javax.swing.JLabel();
        jTextFieldTime = new javax.swing.JTextField();
        jLabelTime = new javax.swing.JLabel();
        jLabelFuncaoTatica = new javax.swing.JLabel();
        jTextFieldTotalCartoesAmarelos = new javax.swing.JTextField();
        jLabelCartoesAmarelos = new javax.swing.JLabel();
        jTextFieldTotalCartoesVermelhos = new javax.swing.JTextField();
        jLabelTotalVitoriasNocaute = new javax.swing.JLabel();
        jLabelPatrocinios = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListPatrocinios = new javax.swing.JList();
        jButtonAdicionarPatrocinio = new javax.swing.JButton();
        jButtonRemoverPatrocinio = new javax.swing.JButton();
        jSpinnerNumeroCamisa = new javax.swing.JSpinner();
        jComboBoxCapitao = new javax.swing.JComboBox();
        jComboBoxFuncaoTatica = new javax.swing.JComboBox();
        jButtonAlterar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jLabelListaFutebolistas = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableListaFutebolistas = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jLabelNome.setText("Nome:");

        jLabelDataNascimento.setText("Data de Nascimento:");

        jLabelAltura.setText("Altura:");

        jTextFieldPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesoActionPerformed(evt);
            }
        });
        jTextFieldPeso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPesoFocusLost(evt);
            }
        });
        jTextFieldPeso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextFieldPesoPropertyChange(evt);
            }
        });

        jLabelPeso.setText("Peso:");

        jLabelNomePai.setText("Nome do Pai:");

        jLabelNomeMae.setText("Nome da Mãe:");

        jLabelSexo.setText("Sexo:");

        jLabelRg.setText("RG:");

        jLabelCpf.setText("CPF:");

        jLabelTelefones.setText("Telefones:");

        jListTelefones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jListTelefones);

        jButtonAdicionarTelefone.setText("+");
        jButtonAdicionarTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarTelefoneActionPerformed(evt);
            }
        });

        jButtonRemoverTelefone.setText("-");
        jButtonRemoverTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverTelefoneActionPerformed(evt);
            }
        });

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Feminino" }));

        jTextFieldDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDataNascimentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTelefones, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRg, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDataNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelSexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelAltura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelPeso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAdicionarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRemoverTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomeMae, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addComponent(jTextFieldNomePai, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldCpf, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldRg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelNomePai, jLabelSexo});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDataNascimento)
                            .addComponent(jTextFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSexo)
                            .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelPeso))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelAltura)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNomePai))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNomeMae))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRg)
                    .addComponent(jTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCpf)
                    .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTelefones)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAdicionarTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoverTelefone))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94))
        );

        jTabbedPane1.addTab("Informações Gerais", jPanel1);

        jLabelLogradouro.setText("Logradouro:");

        jLabelNumero.setText("Número:");

        jLabelBairro.setText("Bairro:");

        jLabelCidade.setText("Cidade:");

        jLabelEstado.setText("Estado:");

        jLabelPais.setText("País:");

        jLabelComplemento.setText("Complemento:");

        jLabelCep.setText("CEP:");

        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelComplemento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelBairro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelLogradouro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldCep, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldPais, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabelLogradouro)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumero)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelComplemento)
                    .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBairro)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCidade)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPais)
                    .addComponent(jTextFieldPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCep)
                    .addComponent(jTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 133, 133))
        );

        jTabbedPane1.addTab("Endereço", jPanel2);

        jLabelCategoria.setText("Categoria:");

        jComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Amador", "Profissional" }));
        jComboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoriaActionPerformed(evt);
            }
        });

        jLabelCpitao.setText("Capitão: ");

        jLabelNumeroDaCamisa.setText("Número da camisa:");

        jLabelTime.setText("Time:");

        jLabelFuncaoTatica.setText("Função Tatica:");

        jLabelCartoesAmarelos.setText("Total de Cartões amarelos:");

        jLabelTotalVitoriasNocaute.setText("Total de Cartões vermelhos:");

        jLabelPatrocinios.setText("Patrocínios:");

        jScrollPane2.setViewportView(jListPatrocinios);

        jButtonAdicionarPatrocinio.setText("+");
        jButtonAdicionarPatrocinio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarPatrocinioActionPerformed(evt);
            }
        });

        jButtonRemoverPatrocinio.setText("-");
        jButtonRemoverPatrocinio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverPatrocinioActionPerformed(evt);
            }
        });

        jSpinnerNumeroCamisa.setModel(new javax.swing.SpinnerNumberModel(1, 1, 999, 1));

        jComboBoxCapitao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "não", "sim" }));

        jComboBoxFuncaoTatica.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Goleiro", "Defesa", "Meio-Campo", "Ataque" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTime)
                    .addComponent(jLabelCategoria)
                    .addComponent(jLabelCpitao)
                    .addComponent(jLabelNumeroDaCamisa)
                    .addComponent(jLabelFuncaoTatica)
                    .addComponent(jLabelCartoesAmarelos)
                    .addComponent(jLabelTotalVitoriasNocaute))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTotalCartoesVermelhos)
                    .addComponent(jTextFieldTotalCartoesAmarelos)
                    .addComponent(jTextFieldTime)
                    .addComponent(jComboBoxCategoria, 0, 234, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinnerNumeroCamisa)
                            .addComponent(jComboBoxCapitao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jComboBoxFuncaoTatica, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPatrocinios)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonRemoverPatrocinio, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(jButtonAdicionarPatrocinio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCategoria)
                            .addComponent(jLabelPatrocinios))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCpitao)
                            .addComponent(jComboBoxCapitao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNumeroDaCamisa)
                            .addComponent(jSpinnerNumeroCamisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTime)
                            .addComponent(jTextFieldTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFuncaoTatica)
                            .addComponent(jComboBoxFuncaoTatica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCartoesAmarelos)
                            .addComponent(jTextFieldTotalCartoesAmarelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTotalVitoriasNocaute)
                            .addComponent(jTextFieldTotalCartoesVermelhos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButtonAdicionarPatrocinio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRemoverPatrocinio))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ficha Técnica", jPanel3);

        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonPesquisar.setText("Pesquisar...");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jLabelListaFutebolistas.setText("Lista de Futebolistas:");

        jTableListaFutebolistas.setModel(new javax.swing.table.DefaultTableModel 
            (
                null,
                new String [] {
                    "Nome", "CPF"
                }
            )
            {
                @Override    
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            });
            jTableListaFutebolistas.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jTableListaFutebolistasMouseClicked(evt);
                }
            });
            jScrollPane4.setViewportView(jTableListaFutebolistas);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelListaFutebolistas)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButtonNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonAlterar)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonExcluir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonPesquisar)
                            .addGap(222, 222, 222)
                            .addComponent(jButtonSalvar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAlterar, jButtonCancelar, jButtonExcluir, jButtonNovo, jButtonPesquisar, jButtonSalvar});

            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonNovo)
                        .addComponent(jButtonAlterar)
                        .addComponent(jButtonExcluir)
                        .addComponent(jButtonPesquisar)
                        .addComponent(jButtonSalvar)
                        .addComponent(jButtonCancelar))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabelListaFutebolistas)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoriaActionPerformed
        this.atualizarCategoria();
    }//GEN-LAST:event_jComboBoxCategoriaActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        umFutebolista = null;
        modoAlteracao = true;
        novoRegistro = true;
        this.limparCampos();
        this.habilitarDesabilitarCampos();
        this.jTextFieldNome.requestFocus();
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        this.salvarRegistro();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if (novoRegistro == true) {
            this.limparCampos();
        } else {
            this.preencherCampos();
        }
        modoAlteracao = false;
        novoRegistro = false;
        this.habilitarDesabilitarCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextFieldPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesoActionPerformed
    }//GEN-LAST:event_jTextFieldPesoActionPerformed

    private void jTextFieldPesoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextFieldPesoPropertyChange
    }//GEN-LAST:event_jTextFieldPesoPropertyChange

    private void jTextFieldPesoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPesoFocusLost
        this.atualizarCategoria();
    }//GEN-LAST:event_jTextFieldPesoFocusLost

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        modoAlteracao = true;
        novoRegistro = false;
        this.habilitarDesabilitarCampos();
        this.jTextFieldNome.requestFocus();
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        this.controleFutebolista.remover(umFutebolista);
        umFutebolista = null;
        this.limparCampos();
        this.carregarListaFutebolistas();
        this.habilitarDesabilitarCampos();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

private void jButtonAdicionarTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarTelefoneActionPerformed
    CadastroTelefone cadastro = new CadastroTelefone(this, true);
    cadastro.setVisible(true);
    if (cadastro.getTelefone() != null) {
        telefonesListModel.addElement(cadastro.getTelefone());
    }
    cadastro.dispose();
}//GEN-LAST:event_jButtonAdicionarTelefoneActionPerformed

private void jButtonRemoverTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverTelefoneActionPerformed
    if (jListTelefones.getSelectedIndex() != -1) {
        telefonesListModel.removeElementAt(jListTelefones.getSelectedIndex());
    }
}//GEN-LAST:event_jButtonRemoverTelefoneActionPerformed

private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
    String pesquisa = JOptionPane.showInputDialog("Informe o nome do Futebolista.");
    if (pesquisa != null) {
        this.pesquisarFutebolista(pesquisa);
    }
}//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void pesquisarFutebolista(String nome) {
        Futebolista futebolistaPesquisado = controleFutebolista.pesquisar(nome);

        if (futebolistaPesquisado == null) {
            exibirInformacao("Futebolista não encontrado.");
        } else {
            this.umFutebolista = futebolistaPesquisado;
            this.preencherCampos();
            this.habilitarDesabilitarCampos();
        }
    }

private void jButtonAdicionarPatrocinioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarPatrocinioActionPerformed
    CadastroPatrocinio cadastro = new CadastroPatrocinio(this, true);
    cadastro.setVisible(true);
    if (cadastro.getPatrocinio() != null) {
        patrocinioListModel.addElement(cadastro.getPatrocinio());
    }
    cadastro.dispose();
}//GEN-LAST:event_jButtonAdicionarPatrocinioActionPerformed

private void jButtonRemoverPatrocinioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverPatrocinioActionPerformed
    if (jListPatrocinios.getSelectedIndex() != -1) {
        patrocinioListModel.removeElementAt(jListPatrocinios.getSelectedIndex());
    }
}//GEN-LAST:event_jButtonRemoverPatrocinioActionPerformed

private void jTableListaFutebolistasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaFutebolistasMouseClicked
    if (jTableListaFutebolistas.isEnabled()) {
        DefaultTableModel model = (DefaultTableModel) jTableListaFutebolistas.getModel();
        String nome = (String) model.getValueAt(jTableListaFutebolistas.getSelectedRow(), 0);
        this.pesquisarFutebolista(nome);
    }
}//GEN-LAST:event_jTableListaFutebolistasMouseClicked

private void jTextFieldDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDataNascimentoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextFieldDataNascimentoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionarPatrocinio;
    private javax.swing.JButton jButtonAdicionarTelefone;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonRemoverPatrocinio;
    private javax.swing.JButton jButtonRemoverTelefone;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxCapitao;
    private javax.swing.JComboBox jComboBoxCategoria;
    private javax.swing.JComboBox jComboBoxEstado;
    private javax.swing.JComboBox jComboBoxFuncaoTatica;
    private javax.swing.JComboBox jComboBoxSexo;
    private javax.swing.JLabel jLabelAltura;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelCartoesAmarelos;
    private javax.swing.JLabel jLabelCategoria;
    private javax.swing.JLabel jLabelCep;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelComplemento;
    private javax.swing.JLabel jLabelCpf;
    private javax.swing.JLabel jLabelCpitao;
    private javax.swing.JLabel jLabelDataNascimento;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelFuncaoTatica;
    private javax.swing.JLabel jLabelListaFutebolistas;
    private javax.swing.JLabel jLabelLogradouro;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNomeMae;
    private javax.swing.JLabel jLabelNomePai;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelNumeroDaCamisa;
    private javax.swing.JLabel jLabelPais;
    private javax.swing.JLabel jLabelPatrocinios;
    private javax.swing.JLabel jLabelPeso;
    private javax.swing.JLabel jLabelRg;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelTelefones;
    private javax.swing.JLabel jLabelTime;
    private javax.swing.JLabel jLabelTotalVitoriasNocaute;
    private javax.swing.JList jListPatrocinios;
    private javax.swing.JList jListTelefones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinnerNumeroCamisa;
    private static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableListaFutebolistas;
    private javax.swing.JTextField jTextFieldAltura;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCep;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldComplemento;
    private javax.swing.JTextField jTextFieldCpf;
    private javax.swing.JTextField jTextFieldDataNascimento;
    private javax.swing.JTextField jTextFieldLogradouro;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNomeMae;
    private javax.swing.JTextField jTextFieldNomePai;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldPais;
    private javax.swing.JTextField jTextFieldPeso;
    private javax.swing.JTextField jTextFieldRg;
    private javax.swing.JTextField jTextFieldTime;
    private javax.swing.JTextField jTextFieldTotalCartoesAmarelos;
    private javax.swing.JTextField jTextFieldTotalCartoesVermelhos;
    // End of variables declaration//GEN-END:variables
}
