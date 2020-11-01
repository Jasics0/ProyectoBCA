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
import DTO.Facturas;
import DTO.Pagos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Retr0
 */
public class PagosJpaController implements Serializable {

    public PagosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagos pagos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturas codigoFactura = pagos.getCodigoFactura();
            if (codigoFactura != null) {
                codigoFactura = em.getReference(codigoFactura.getClass(), codigoFactura.getCodigoFactura());
                pagos.setCodigoFactura(codigoFactura);
            }
            em.persist(pagos);
            if (codigoFactura != null) {
                codigoFactura.getPagosCollection().add(pagos);
                codigoFactura = em.merge(codigoFactura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPagos(pagos.getCodigoPago()) != null) {
                throw new PreexistingEntityException("Pagos " + pagos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagos pagos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagos persistentPagos = em.find(Pagos.class, pagos.getCodigoPago());
            Facturas codigoFacturaOld = persistentPagos.getCodigoFactura();
            Facturas codigoFacturaNew = pagos.getCodigoFactura();
            if (codigoFacturaNew != null) {
                codigoFacturaNew = em.getReference(codigoFacturaNew.getClass(), codigoFacturaNew.getCodigoFactura());
                pagos.setCodigoFactura(codigoFacturaNew);
            }
            pagos = em.merge(pagos);
            if (codigoFacturaOld != null && !codigoFacturaOld.equals(codigoFacturaNew)) {
                codigoFacturaOld.getPagosCollection().remove(pagos);
                codigoFacturaOld = em.merge(codigoFacturaOld);
            }
            if (codigoFacturaNew != null && !codigoFacturaNew.equals(codigoFacturaOld)) {
                codigoFacturaNew.getPagosCollection().add(pagos);
                codigoFacturaNew = em.merge(codigoFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pagos.getCodigoPago();
                if (findPagos(id) == null) {
                    throw new NonexistentEntityException("The pagos with id " + id + " no longer exists.");
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
            Pagos pagos;
            try {
                pagos = em.getReference(Pagos.class, id);
                pagos.getCodigoPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagos with id " + id + " no longer exists.", enfe);
            }
            Facturas codigoFactura = pagos.getCodigoFactura();
            if (codigoFactura != null) {
                codigoFactura.getPagosCollection().remove(pagos);
                codigoFactura = em.merge(codigoFactura);
            }
            em.remove(pagos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagos> findPagosEntities() {
        return findPagosEntities(true, -1, -1);
    }

    public List<Pagos> findPagosEntities(int maxResults, int firstResult) {
        return findPagosEntities(false, maxResults, firstResult);
    }

    private List<Pagos> findPagosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagos.class));
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

    public Pagos findPagos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagos> rt = cq.from(Pagos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
