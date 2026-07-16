package cliente;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private int cep;
    private String estado;



public Cliente(){

    }

    public Cliente(String nome, String email, String cpf, String rua, int numero, String bairro, String cidade, int cep, String estado){
        setNome(nome);
        setEmail(email);
        setCpf(cpf);
        setRua(rua);
        setNumero(numero);
        setBairro(bairro);
        setCidade(cidade);
        setCep(cep);
        setEstado(estado);
    }

    public Cliente(int id, String nome, String email, String cpf, String rua, int numero, String bairro, String cidade, int cep, String estado){
        setId(id);
        setNome(nome);
        setEmail(email);
        setCpf(cpf);
        setRua(rua);
        setNumero(numero);
        setBairro(bairro);
        setCidade(cidade);
        setCep(cep);
        setEstado(estado);
    }

    //Getters
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getRua() {
        return this.rua;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public int getCep() {
        return this.cep;
    }

    public String getEstado() {
        return this.estado;
    }
    

    //Setters
    public void setId(int id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public void setRua(String rua){
        this.rua = rua;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public void setCep(int cep){
        this.cep = cep;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }
}