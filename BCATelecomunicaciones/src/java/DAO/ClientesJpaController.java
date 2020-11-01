/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.Clientes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Planes;
import DTO.Tickets;
import java.util.ArrayList;
import java.util.Collection;
import DTO.Facturas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Retr0
 */
public class ClientesJpaController implements Serializable {

    public ClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientes clientes) throws PreexistingEntityException, Exception {
        if (clientes.getTicketsCollection() == null) {
            clientes.setTicketsCollection(new ArrayList<Tickets>());
        }
        if (clientes.getFacturasCollection() == null) {
            clientes.setFacturasCollection(new ArrayList<Facturas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planes plan = clientes.getPlan();
            if (plan != null) {
                plan = em.getReference(plan.getClass(), plan.getCodProducto());
                clientes.setPlan(plan);
            }
            Collection<Tickets> attachedTicketsCollection = new ArrayList<Tickets>();
            for (Tickets ticketsCollectionTicketsToAttach : clientes.getTicketsCollection()) {
                ticketsCollectionTicketsToAttach = em.getReference(ticketsCollectionTicketsToAttach.getClass(), ticketsCollectionTicketsToAttach.getCodigoTicket());
                attachedTicketsCollection.add(ticketsCollectionTicketsToAttach);
            }
            clientes.setTicketsCollection(attachedTicketsCollection);
            Collection<Facturas> attachedFacturasCollection = new ArrayList<Facturas>();
            for (Facturas facturasCollectionFacturasToAttach : clientes.getFacturasCollection()) {
                facturasCollectionFacturasToAttach = em.getReference(facturasCollectionFacturasToAttach.getClass(), facturasCollectionFacturasToAttach.getCodigoFactura());
                attachedFacturasCollection.add(facturasCollectionFacturasToAttach);
            }
            clientes.setFacturasCollection(attachedFacturasCollection);
            em.persist(clientes);
            if (plan != null) {
                plan.getClientesCollection().add(clientes);
                plan = em.merge(plan);
            }
            for (Tickets ticketsCollectionTickets : clientes.getTicketsCollection()) {
                Clientes oldIdClienteOfTicketsCollectionTickets = ticketsCollectionTickets.getIdCliente();
                ticketsCollectionTickets.setIdCliente(clientes);
                ticketsCollectionTickets = em.merge(ticketsCollectionTickets);
                if (oldIdClienteOfTicketsCollectionTickets != null) {
                    oldIdClienteOfTicketsCollectionTickets.getTicketsCollection().remove(ticketsCollectionTickets);
                    oldIdClienteOfTicketsCollectionTickets = em.merge(oldIdClienteOfTicketsCollectionTickets);
                }
            }
            for (Facturas facturasCollectionFacturas : clientes.getFacturasCollection()) {
                Clientes oldIdClienteOfFacturasCollectionFacturas = facturasCollectionFacturas.getIdCliente();
                facturasCollectionFacturas.setIdCliente(clientes);
                facturasCollectionFacturas = em.merge(facturasCollectionFacturas);
                if (oldIdClienteOfFacturasCollectionFacturas != null) {
                    oldIdClienteOfFacturasCollectionFacturas.getFacturasCollection().remove(facturasCollectionFacturas);
                    oldIdClienteOfFacturasCollectionFacturas = em.merge(oldIdClienteOfFacturasCollectionFacturas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClientes(clientes.getIdCliente()) != null) {
                throw new PreexistingEntityException("Clientes " + clientes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientes clientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes persistentClientes = em.find(Clientes.class, clientes.getIdCliente());
            Planes planOld = persistentClientes.getPlan();
            Planes planNew = clientes.getPlan();
            Collection<Tickets> ticketsCollectionOld = persistentClientes.getTicketsCollection();
            Collection<Tickets> ticketsCollectionNew = clientes.getTicketsCollection();
            Collection<Facturas> facturasCollectionOld = persistentClientes.getFacturasCollection();
            Collection<Facturas> facturasCollectionNew = clientes.getFacturasCollection();
            if (planNew != null) {
                planNew = em.getReference(planNew.getClass(), planNew.getCodProducto());
                clientes.setPlan(planNew);
            }
            Collection<Tickets> attachedTicketsCollectionNew = new ArrayList<Tickets>();
            for (Tickets ticketsCollectionNewTicketsToAttach : ticketsCollectionNew) {
                ticketsCollectionNewTicketsToAttach = em.getReference(ticketsCollectionNewTicketsToAttach.getClass(), ticketsCollectionNewTicketsToAttach.getCodigoTicket());
                attachedTicketsCollectionNew.add(ticketsCollectionNewTicketsToAttach);
            }
            ticketsCollectionNew = attachedTicketsCollectionNew;
            clientes.setTicketsCollection(ticketsCollectionNew);
            Collection<Facturas> attachedFacturasCollectionNew = new ArrayList<Facturas>();
            for (Facturas facturasCollectionNewFacturasToAttach : facturasCollectionNew) {
                facturasCollectionNewFacturasToAttach = em.getReference(facturasCollectionNewFacturasToAttach.getClass(), facturasCollectionNewFacturasToAttach.getCodigoFactura());
                attachedFacturasCollectionNew.add(facturasCollectionNewFacturasToAttach);
            }
            facturasCollectionNew = attachedFacturasCollectionNew;
            clientes.setFacturasCollection(facturasCollectionNew);
            clientes = em.merge(clientes);
            if (planOld != null && !planOld.equals(planNew)) {
                planOld.getClientesCollection().remove(clientes);
                planOld = em.merge(planOld);
            }
            if (planNew != null && !planNew.equals(planOld)) {
                planNew.getClientesCollection().add(clientes);
                planNew = em.merge(planNew);
            }
            for (Tickets ticketsCollectionOldTickets : ticketsCollectionOld) {
                if (!ticketsCollectionNew.contains(ticketsCollectionOldTickets)) {
                    ticketsCollectionOldTickets.setIdCliente(null);
                    ticketsCollectionOldTickets = em.merge(ticketsCollectionOldTickets);
                }
            }
            for (Tickets ticketsCollectionNewTickets : ticketsCollectionNew) {
                if (!ticketsCollectionOld.contains(ticketsCollectionNewTickets)) {
                    Clientes oldIdClienteOfTicketsCollectionNewTickets = ticketsCollectionNewTickets.getIdCliente();
                    ticketsCollectionNewTickets.setIdCliente(clientes);
                    ticketsCollectionNewTickets = em.merge(ticketsCollectionNewTickets);
                    if (oldIdClienteOfTicketsCollectionNewTickets != null && !oldIdClienteOfTicketsCollectionNewTickets.equals(clientes)) {
                        oldIdClienteOfTicketsCollectionNewTickets.getTicketsCollection().remove(ticketsCollectionNewTickets);
                        oldIdClienteOfTicketsCollectionNewTickets = em.merge(oldIdClienteOfTicketsCollectionNewTickets);
                    }
                }
            }
            for (Facturas facturasCollectionOldFacturas : facturasCollectionOld) {
                if (!facturasCollectionNew.contains(facturasCollectionOldFacturas)) {
                    facturasCollectionOldFacturas.setIdCliente(null);
                    facturasCollectionOldFacturas = em.merge(facturasCollectionOldFacturas);
                }
            }
            for (Facturas facturasCollectionNewFacturas : facturasCollectionNew) {
                if (!facturasCollectionOld.contains(facturasCollectionNewFacturas)) {
                    Clientes oldIdClienteOfFacturasCollectionNewFacturas = facturasCollectionNewFacturas.getIdCliente();
                    facturasCollectionNewFacturas.setIdCliente(clientes);
                    facturasCollectionNewFacturas = em.merge(facturasCollectionNewFacturas);
                    if (oldIdClienteOfFacturasCollectionNewFacturas != null && !oldIdClienteOfFacturasCollectionNewFacturas.equals(clientes)) {
                        oldIdClienteOfFacturasCollectionNewFacturas.getFacturasCollection().remove(facturasCollectionNewFacturas);
                        oldIdClienteOfFacturasCollectionNewFacturas = em.merge(oldIdClienteOfFacturasCollectionNewFacturas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clientes.getIdCliente();
                if (findClientes(id) == null) {
                    throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }
            Planes plan = clientes.getPlan();
            if (plan != null) {
                plan.getClientesCollection().remove(clientes);
                plan = em.merge(plan);
            }
            Collection<Tickets> ticketsCollection = clientes.getTicketsCollection();
            for (Tickets ticketsCollectionTickets : ticketsCollection) {
                ticketsCollectionTickets.setIdCliente(null);
                ticketsCollectionTickets = em.merge(ticketsCollectionTickets);
            }
            Collection<Facturas> facturasCollection = clientes.getFacturasCollection();
            for (Facturas facturasCollectionFacturas : facturasCollection) {
                facturasCollectionFacturas.setIdCliente(null);
                facturasCollectionFacturas = em.merge(facturasCollectionFacturas);
            }
            em.remove(clientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
    }

    private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientes.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Clientes findClientes(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientes> rt = cq.from(Clientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
