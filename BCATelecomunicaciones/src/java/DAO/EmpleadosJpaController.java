/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.Empleados;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Usuarios;
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
public class EmpleadosJpaController implements Serializable {

    public EmpleadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleados empleados) throws PreexistingEntityException, Exception {
        if (empleados.getTicketsCollection() == null) {
            empleados.setTicketsCollection(new ArrayList<Tickets>());
        }
        if (empleados.getFacturasCollection() == null) {
            empleados.setFacturasCollection(new ArrayList<Facturas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios username = empleados.getUser();
            if (username != null) {
                username = em.getReference(username.getClass(), username.getUsername());
                empleados.setUser(username);
            }
            Collection<Tickets> attachedTicketsCollection = new ArrayList<Tickets>();
            for (Tickets ticketsCollectionTicketsToAttach : empleados.getTicketsCollection()) {
                ticketsCollectionTicketsToAttach = em.getReference(ticketsCollectionTicketsToAttach.getClass(), ticketsCollectionTicketsToAttach.getCodigoTicket());
                attachedTicketsCollection.add(ticketsCollectionTicketsToAttach);
            }
            empleados.setTicketsCollection(attachedTicketsCollection);
            Collection<Facturas> attachedFacturasCollection = new ArrayList<Facturas>();
            for (Facturas facturasCollectionFacturasToAttach : empleados.getFacturasCollection()) {
                facturasCollectionFacturasToAttach = em.getReference(facturasCollectionFacturasToAttach.getClass(), facturasCollectionFacturasToAttach.getCodigoFactura());
                attachedFacturasCollection.add(facturasCollectionFacturasToAttach);
            }
            empleados.setFacturasCollection(attachedFacturasCollection);
            em.persist(empleados);
            if (username != null) {
                username.getEmpleadosCollection().add(empleados);
                username = em.merge(username);
            }
            for (Tickets ticketsCollectionTickets : empleados.getTicketsCollection()) {
                Empleados oldIdEmpleadoOfTicketsCollectionTickets = ticketsCollectionTickets.getIdEmpleado();
                ticketsCollectionTickets.setIdEmpleado(empleados);
                ticketsCollectionTickets = em.merge(ticketsCollectionTickets);
                if (oldIdEmpleadoOfTicketsCollectionTickets != null) {
                    oldIdEmpleadoOfTicketsCollectionTickets.getTicketsCollection().remove(ticketsCollectionTickets);
                    oldIdEmpleadoOfTicketsCollectionTickets = em.merge(oldIdEmpleadoOfTicketsCollectionTickets);
                }
            }
            for (Facturas facturasCollectionFacturas : empleados.getFacturasCollection()) {
                Empleados oldIdEmpleadoOfFacturasCollectionFacturas = facturasCollectionFacturas.getIdEmpleado();
                facturasCollectionFacturas.setIdEmpleado(empleados);
                facturasCollectionFacturas = em.merge(facturasCollectionFacturas);
                if (oldIdEmpleadoOfFacturasCollectionFacturas != null) {
                    oldIdEmpleadoOfFacturasCollectionFacturas.getFacturasCollection().remove(facturasCollectionFacturas);
                    oldIdEmpleadoOfFacturasCollectionFacturas = em.merge(oldIdEmpleadoOfFacturasCollectionFacturas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleados(empleados.getIdEmpleado()) != null) {
                throw new PreexistingEntityException("Empleados " + empleados + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleados empleados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados persistentEmpleados = em.find(Empleados.class, empleados.getIdEmpleado());
            Usuarios usernameOld = persistentEmpleados.getUser();
            Usuarios usernameNew = empleados.getUser();
            Collection<Tickets> ticketsCollectionOld = persistentEmpleados.getTicketsCollection();
            Collection<Tickets> ticketsCollectionNew = empleados.getTicketsCollection();
            Collection<Facturas> facturasCollectionOld = persistentEmpleados.getFacturasCollection();
            Collection<Facturas> facturasCollectionNew = empleados.getFacturasCollection();
            if (usernameNew != null) {
                usernameNew = em.getReference(usernameNew.getClass(), usernameNew.getUsername());
                empleados.setUser(usernameNew);
            }
            Collection<Tickets> attachedTicketsCollectionNew = new ArrayList<Tickets>();
            for (Tickets ticketsCollectionNewTicketsToAttach : ticketsCollectionNew) {
                ticketsCollectionNewTicketsToAttach = em.getReference(ticketsCollectionNewTicketsToAttach.getClass(), ticketsCollectionNewTicketsToAttach.getCodigoTicket());
                attachedTicketsCollectionNew.add(ticketsCollectionNewTicketsToAttach);
            }
            ticketsCollectionNew = attachedTicketsCollectionNew;
            empleados.setTicketsCollection(ticketsCollectionNew);
            Collection<Facturas> attachedFacturasCollectionNew = new ArrayList<Facturas>();
            for (Facturas facturasCollectionNewFacturasToAttach : facturasCollectionNew) {
                facturasCollectionNewFacturasToAttach = em.getReference(facturasCollectionNewFacturasToAttach.getClass(), facturasCollectionNewFacturasToAttach.getCodigoFactura());
                attachedFacturasCollectionNew.add(facturasCollectionNewFacturasToAttach);
            }
            facturasCollectionNew = attachedFacturasCollectionNew;
            empleados.setFacturasCollection(facturasCollectionNew);
            empleados = em.merge(empleados);
            if (usernameOld != null && !usernameOld.equals(usernameNew)) {
                usernameOld.getEmpleadosCollection().remove(empleados);
                usernameOld = em.merge(usernameOld);
            }
            if (usernameNew != null && !usernameNew.equals(usernameOld)) {
                usernameNew.getEmpleadosCollection().add(empleados);
                usernameNew = em.merge(usernameNew);
            }
            for (Tickets ticketsCollectionOldTickets : ticketsCollectionOld) {
                if (!ticketsCollectionNew.contains(ticketsCollectionOldTickets)) {
                    ticketsCollectionOldTickets.setIdEmpleado(null);
                    ticketsCollectionOldTickets = em.merge(ticketsCollectionOldTickets);
                }
            }
            for (Tickets ticketsCollectionNewTickets : ticketsCollectionNew) {
                if (!ticketsCollectionOld.contains(ticketsCollectionNewTickets)) {
                    Empleados oldIdEmpleadoOfTicketsCollectionNewTickets = ticketsCollectionNewTickets.getIdEmpleado();
                    ticketsCollectionNewTickets.setIdEmpleado(empleados);
                    ticketsCollectionNewTickets = em.merge(ticketsCollectionNewTickets);
                    if (oldIdEmpleadoOfTicketsCollectionNewTickets != null && !oldIdEmpleadoOfTicketsCollectionNewTickets.equals(empleados)) {
                        oldIdEmpleadoOfTicketsCollectionNewTickets.getTicketsCollection().remove(ticketsCollectionNewTickets);
                        oldIdEmpleadoOfTicketsCollectionNewTickets = em.merge(oldIdEmpleadoOfTicketsCollectionNewTickets);
                    }
                }
            }
            for (Facturas facturasCollectionOldFacturas : facturasCollectionOld) {
                if (!facturasCollectionNew.contains(facturasCollectionOldFacturas)) {
                    facturasCollectionOldFacturas.setIdEmpleado(null);
                    facturasCollectionOldFacturas = em.merge(facturasCollectionOldFacturas);
                }
            }
            for (Facturas facturasCollectionNewFacturas : facturasCollectionNew) {
                if (!facturasCollectionOld.contains(facturasCollectionNewFacturas)) {
                    Empleados oldIdEmpleadoOfFacturasCollectionNewFacturas = facturasCollectionNewFacturas.getIdEmpleado();
                    facturasCollectionNewFacturas.setIdEmpleado(empleados);
                    facturasCollectionNewFacturas = em.merge(facturasCollectionNewFacturas);
                    if (oldIdEmpleadoOfFacturasCollectionNewFacturas != null && !oldIdEmpleadoOfFacturasCollectionNewFacturas.equals(empleados)) {
                        oldIdEmpleadoOfFacturasCollectionNewFacturas.getFacturasCollection().remove(facturasCollectionNewFacturas);
                        oldIdEmpleadoOfFacturasCollectionNewFacturas = em.merge(oldIdEmpleadoOfFacturasCollectionNewFacturas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = empleados.getIdEmpleado();
                if (findEmpleados(id) == null) {
                    throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.");
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
            Empleados empleados;
            try {
                empleados = em.getReference(Empleados.class, id);
                empleados.getIdEmpleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.", enfe);
            }
            Usuarios username = empleados.getUser();
            if (username != null) {
                username.getEmpleadosCollection().remove(empleados);
                username = em.merge(username);
            }
            Collection<Tickets> ticketsCollection = empleados.getTicketsCollection();
            for (Tickets ticketsCollectionTickets : ticketsCollection) {
                ticketsCollectionTickets.setIdEmpleado(null);
                ticketsCollectionTickets = em.merge(ticketsCollectionTickets);
            }
            Collection<Facturas> facturasCollection = empleados.getFacturasCollection();
            for (Facturas facturasCollectionFacturas : facturasCollection) {
                facturasCollectionFacturas.setIdEmpleado(null);
                facturasCollectionFacturas = em.merge(facturasCollectionFacturas);
            }
            em.remove(empleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleados> findEmpleadosEntities() {
        return findEmpleadosEntities(true, -1, -1);
    }

    public List<Empleados> findEmpleadosEntities(int maxResults, int firstResult) {
        return findEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<Empleados> findEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleados.class));
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

    public Empleados findEmpleados(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleados.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleados> rt = cq.from(Empleados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
