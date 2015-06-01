package travel.teste.com.br.travel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Info implements Serializable {
    String status;

    @SerializedName("total_pacotes")
    int totalPacotes;

    String empresa;

    List<Pacotes> pacotes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalCount() {
        return totalPacotes;
    }

    public void setTotalCount(int totalCount) {
        this.totalPacotes = totalPacotes;
    }

    public String getempresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public List<Pacotes> getPacotes() {
        return pacotes;
    }

    public void setPacotes(List<Pacotes> pacotes) {
        this.pacotes = pacotes;
    }
}
