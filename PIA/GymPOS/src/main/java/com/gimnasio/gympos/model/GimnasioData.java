package com.gimnasio.gympos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GimnasioData implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Cliente> listaClientes;

    public GimnasioData() {
        this.listaClientes = new ArrayList<>();
    }

    public List<Cliente> getListaClientes() { return listaClientes; }
    public void setListaClientes(List<Cliente> listaClientes) { this.listaClientes = listaClientes; }
}