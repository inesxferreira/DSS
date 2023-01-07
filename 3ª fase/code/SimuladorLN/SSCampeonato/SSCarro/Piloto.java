package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class SC here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public class Piloto {
    private Integer idPiloto;
	private String nome;
	private Float cts;
	private Float sva;

    /*Construtores*/
    public Piloto()
    {
        this.idPiloto = 0;
        this.nome = "";
        this.cts = 0.0f;
        this.sva = 0.0f;
    }
    
    public Piloto(Integer idPiloto, String nome, Float cts, Float sva)
    {
        this.idPiloto = idPiloto;
        this.nome = nome;
        this.cts = cts;
        this.sva = sva;
    }
    
    public Piloto(Piloto p)
    {
        this.idPiloto = p.getIdPiloto();
        this.nome = p.getNome();
        this.cts = p.getCTS();
        this.sva = p.getSVA();
    }


	/* Getters */
	public Integer getIdPiloto() {
		return this.idPiloto;
	}

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
	public void setIdPiloto(Integer idPiloto) {
		this.idPiloto = idPiloto;
	}

    public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCTS(Float cts) {
		this.cts = cts;
	}

	public void setSVA(Float sva) {
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