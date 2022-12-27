package SimuladorLN.SSCampeonato.SSCarro;

public class Piloto {

	private String nome;
	private Float cts;
	private Float sva;

    /*Construtores*/
    public Piloto()
    {
        this.nome = "";
        this.cts = 0.0f;
        this.sva = 0.0f;
    }
    
    public Piloto(String nome, Float cts, Float sva)
    {
        this.nome = nome;
        this.cts = cts;
        this.sva = sva;
    }
    
    public Piloto(Piloto p)
    {
        this.nome = p.getNome();
        this.cts = p.getCTS();
        this.sva = p.getSVA();
    }


	/* Getters */
	public String getNome() {
		return this.nome;
	}

	public Float getCTS() {
		return this.cts;
	}

	public Float getSVA() {
		return this.sva;
	}

	/* Setters */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void getCTS(Float cts) {
		this.cts = cts;
	}

	public void getSVA(Float sva) {
		this.sva = sva;
	}

	/* Metodos usuais */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome: "); sb.append(this.nome);
        sb.append("\tChuva vs Tempo Seco: ");sb.append(this.cts);
        sb.append("\tSVA: ");sb.append(this.sva);
        return sb.toString();
    }
    
    public boolean equals(Object o)
    {
        if(this == o)
        return true;
        
        if((o == null) || (this.getClass() != o.getClass()))
        return false;
        
        Piloto p = (Piloto) o;
        return (this.nome.equals(p.getNome()) && 
                this.cts == p.getCTS() &&
                this.sva == p.getSVA());
    }

	public Piloto clone()
    {
        return new Piloto(this);
    }
}