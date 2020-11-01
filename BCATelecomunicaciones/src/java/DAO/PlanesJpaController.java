/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Clientes;
import DTO.Planes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Retr0
 */
public class PlanesJpaController implements Serializable {

    public PlanesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planes planes) throws PreexistingEntityException, Exception {
        if (planes.getClientesCollection() == null) {
            planes.setClientesCollection(new ArrayList<Clientes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Clientes> attachedClientesCollection = new ArrayList<Clientes>();
            for (Clientes clientesCollectionClientesToAttach : planes.getClientesCollection()) {
                clientesCollectionClientesToAttach = em.getReference(clientesCollectionClientesToAttach.getClass(), clientesCollectionClientesToAttach.getIdCliente());
                attachedClientesCollection.add(clientesCollectionClientesToAttach);
            }
            planes.setClientesCollection(attachedClientesCollection);
            em.persist(planes);
            for (Clientes clientesCollectionClientes : planes.getClientesCollection()) {
                Planes oldPlanOfClientesCollectionClientes = clientesCollectionClientes.getPlan();
                clientesCollectionClientes.setPlan(planes);
                clientesCollectionClientes = em.merge(clientesCollectionClientes);
                if (oldPlanOfClientesCollectionClientes != null) {
                    oldPlanOfClientesCollectionClientes.getClientesCollection().remove(clientesCollectionClientes);
                    oldPlanOfClientesCollectionClientes = em.merge(oldPlanOfClientesCollectionClientes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanes(planes.getCodProducto()) != null) {
                throw new PreexistingEntityException("Planes " + planes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planes planes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planes persistentPlanes = em.find(Planes.class, planes.getCodProducto());
            Collection<Clientes> clientesCollectionOld = persistentPlanes.getClientesCollection();
            Collection<Clientes> clientesCollectionNew = planes.getClientesCollection();
            Collection<Clientes> attachedClientesCollectionNew = new ArrayList<Clientes>();
            for (Clientes clientesCollectionNewClientesToAttach : clientesCollectionNew) {
                clientesCollectionNewClientesToAttach = em.getReference(clientesCollectionNewClientesToAttach.getClass(), clientesCollectionNewClientesToAttach.getIdCliente());
                attachedClientesCollectionNew.add(clientesCollectionNewClientesToAttach);
            }
            clientesCollectionNew = attachedClientesCollectionNew;
            planes.setClientesCollection(clientesCollectionNew);
            planes = em.merge(planes);
            for (Clientes clientesCollectionOldClientes : clientesCollectionOld) {
                if (!clientesCollectionNew.contains(clientesCollectionOldClientes)) {
                    clientesCollectionOldClientes.setPlan(null);
                    clientesCollectionOldClientes = em.merge(clientesCollectionOldClientes);
                }
            }
            for (Clientes clientesCollectionNewClientes : clientesCollectionNew) {
                if (!clientesCollectionOld.contains(clientesCollectionNewClientes)) {
                    Planes oldPlanOfClientesCollectionNewClientes = clientesCollectionNewClientes.getPlan();
                    clientesCollectionNewClientes.setPlan(planes);
                    clientesCollectionNewClientes = em.merge(clientesCollectionNewClientes);
                    if (oldPlanOfClientesCollectionNewClientes != null && !oldPlanOfClientesCollectionNewClientes.equals(planes)) {
                        oldPlanOfClientesCollectionNewClientes.getClientesCollection().remove(clientesCollectionNewClientes);
                        oldPlanOfClientesCollectionNewClientes = em.merge(oldPlanOfClientesCollectionNewClientes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = planes.getCodProducto();
                if (findPlanes(id) == null) {
                    throw new NonexistentEntityException("The planes with id " + id + " no longer exists.");
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
            Planes planes;
            try {
                planes = em.getReference(Planes.class, id);
                planes.getCodProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planes with id " + id + " no longer exists.", enfe);
            }
            Collection<Clientes> clientesCollection = planes.getClientesCollection();
            for (Clientes clientesCollectionClientes : clientesCollection) {
                clientesCollectionClientes.setPlan(null);
                clientesCollectionClientes = em.merge(clientesCollectionClientes);
            }
            em.remove(planes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planes> findPlanesEntities() {
        return findPlanesEntities(true, -1, -1);
    }

    public List<Planes> findPlanesEntities(int maxResults, int firstResult) {
        return findPlanesEntities(false, maxResults, firstResult);
    }

    private List<Planes> findPlanesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planes.class));
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

    public Planes findPlanes(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planes.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planes> rt = cq.from(Planes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    
}
