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
import DTO.Empleados;
import DTO.Facturas;
import DTO.Pagos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Retr0
 */
public class FacturasJpaController implements Serializable {

    public FacturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facturas facturas) throws PreexistingEntityException, Exception {
        if (facturas.getPagosCollection() == null) {
            facturas.setPagosCollection(new ArrayList<Pagos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes idCliente = facturas.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                facturas.setIdCliente(idCliente);
            }
            Empleados idEmpleado = facturas.getIdEmpleado();
            if (idEmpleado != null) {
                idEmpleado = em.getReference(idEmpleado.getClass(), idEmpleado.getIdEmpleado());
                facturas.setIdEmpleado(idEmpleado);
            }
            Collection<Pagos> attachedPagosCollection = new ArrayList<Pagos>();
            for (Pagos pagosCollectionPagosToAttach : facturas.getPagosCollection()) {
                pagosCollectionPagosToAttach = em.getReference(pagosCollectionPagosToAttach.getClass(), pagosCollectionPagosToAttach.getCodigoPago());
                attachedPagosCollection.add(pagosCollectionPagosToAttach);
            }
            facturas.setPagosCollection(attachedPagosCollection);
            em.persist(facturas);
            if (idCliente != null) {
                idCliente.getFacturasCollection().add(facturas);
                idCliente = em.merge(idCliente);
            }
            if (idEmpleado != null) {
                idEmpleado.getFacturasCollection().add(facturas);
                idEmpleado = em.merge(idEmpleado);
            }
            for (Pagos pagosCollectionPagos : facturas.getPagosCollection()) {
                Facturas oldCodigoFacturaOfPagosCollectionPagos = pagosCollectionPagos.getCodigoFactura();
                pagosCollectionPagos.setCodigoFactura(facturas);
                pagosCollectionPagos = em.merge(pagosCollectionPagos);
                if (oldCodigoFacturaOfPagosCollectionPagos != null) {
                    oldCodigoFacturaOfPagosCollectionPagos.getPagosCollection().remove(pagosCollectionPagos);
                    oldCodigoFacturaOfPagosCollectionPagos = em.merge(oldCodigoFacturaOfPagosCollectionPagos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacturas(facturas.getCodigoFactura()) != null) {
                throw new PreexistingEntityException("Facturas " + facturas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturas facturas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturas persistentFacturas = em.find(Facturas.class, facturas.getCodigoFactura());
            Clientes idClienteOld = persistentFacturas.getIdCliente();
            Clientes idClienteNew = facturas.getIdCliente();
            Empleados idEmpleadoOld = persistentFacturas.getIdEmpleado();
            Empleados idEmpleadoNew = facturas.getIdEmpleado();
            Collection<Pagos> pagosCollectionOld = persistentFacturas.getPagosCollection();
            Collection<Pagos> pagosCollectionNew = facturas.getPagosCollection();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                facturas.setIdCliente(idClienteNew);
            }
            if (idEmpleadoNew != null) {
                idEmpleadoNew = em.getReference(idEmpleadoNew.getClass(), idEmpleadoNew.getIdEmpleado());
                facturas.setIdEmpleado(idEmpleadoNew);
            }
            Collection<Pagos> attachedPagosCollectionNew = new ArrayList<Pagos>();
            for (Pagos pagosCollectionNewPagosToAttach : pagosCollectionNew) {
                pagosCollectionNewPagosToAttach = em.getReference(pagosCollectionNewPagosToAttach.getClass(), pagosCollectionNewPagosToAttach.getCodigoPago());
                attachedPagosCollectionNew.add(pagosCollectionNewPagosToAttach);
            }
            pagosCollectionNew = attachedPagosCollectionNew;
            facturas.setPagosCollection(pagosCollectionNew);
            facturas = em.merge(facturas);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getFacturasCollection().remove(facturas);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getFacturasCollection().add(facturas);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idEmpleadoOld != null && !idEmpleadoOld.equals(idEmpleadoNew)) {
                idEmpleadoOld.getFacturasCollection().remove(facturas);
                idEmpleadoOld = em.merge(idEmpleadoOld);
            }
            if (idEmpleadoNew != null && !idEmpleadoNew.equals(idEmpleadoOld)) {
                idEmpleadoNew.getFacturasCollection().add(facturas);
                idEmpleadoNew = em.merge(idEmpleadoNew);
            }
            for (Pagos pagosCollectionOldPagos : pagosCollectionOld) {
                if (!pagosCollectionNew.contains(pagosCollectionOldPagos)) {
                    pagosCollectionOldPagos.setCodigoFactura(null);
                    pagosCollectionOldPagos = em.merge(pagosCollectionOldPagos);
                }
            }
            for (Pagos pagosCollectionNewPagos : pagosCollectionNew) {
                if (!pagosCollectionOld.contains(pagosCollectionNewPagos)) {
                    Facturas oldCodigoFacturaOfPagosCollectionNewPagos = pagosCollectionNewPagos.getCodigoFactura();
                    pagosCollectionNewPagos.setCodigoFactura(facturas);
                    pagosCollectionNewPagos = em.merge(pagosCollectionNewPagos);
                    if (oldCodigoFacturaOfPagosCollectionNewPagos != null && !oldCodigoFacturaOfPagosCollectionNewPagos.equals(facturas)) {
                        oldCodigoFacturaOfPagosCollectionNewPagos.getPagosCollection().remove(pagosCollectionNewPagos);
                        oldCodigoFacturaOfPagosCollectionNewPagos = em.merge(oldCodigoFacturaOfPagosCollectionNewPagos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = facturas.getCodigoFactura();
                if (findFacturas(id) == null) {
                    throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.");
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
            Facturas facturas;
            try {
                facturas = em.getReference(Facturas.class, id);
                facturas.getCodigoFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.", enfe);
            }
            Clientes idCliente = facturas.getIdCliente();
            if (idCliente != null) {
                idCliente.getFacturasCollection().remove(facturas);
                idCliente = em.merge(idCliente);
            }
            Empleados idEmpleado = facturas.getIdEmpleado();
            if (idEmpleado != null) {
                idEmpleado.getFacturasCollection().remove(facturas);
                idEmpleado = em.merge(idEmpleado);
            }
            Collection<Pagos> pagosCollection = facturas.getPagosCollection();
            for (Pagos pagosCollectionPagos : pagosCollection) {
                pagosCollectionPagos.setCodigoFactura(null);
                pagosCollectionPagos = em.merge(pagosCollectionPagos);
            }
            em.remove(facturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturas> findFacturasEntities() {
        return findFacturasEntities(true, -1, -1);
    }

    public List<Facturas> findFacturasEntities(int maxResults, int firstResult) {
        return findFacturasEntities(false, maxResults, firstResult);
    }

    private List<Facturas> findFacturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturas.class));
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

    public Facturas findFacturas(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturas> rt = cq.from(Facturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
