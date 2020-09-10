package com.javatraining.javaorders.services;

import com.javatraining.javaorders.models.Agent;
import com.javatraining.javaorders.models.Customer;
import com.javatraining.javaorders.models.Order;
import com.javatraining.javaorders.models.Payment;
import com.javatraining.javaorders.repositories.CustomersRepository;
import com.javatraining.javaorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import views.OrderCounts;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

//Implementation determines rules for how business rules in Service will be validated and responded to.

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomersRepository custrepos;

    @Autowired
    OrdersRepository ordrepos;

    @Override
    public List<Customer> findAllCustomerOrders()
    {
        List<Customer> list = new ArrayList<>();

        custrepos.findAll().iterator().forEachRemaining(list :: add);

        return list;
    }

    @Override
    public Customer findCustomerById(Long custId) throws
            EntityNotFoundException
    {
        return custrepos.findById(custId)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + custId + " not found!"));
    }


    @Override
    public List<Customer> findByNameLike(String name) {
        List<Customer> list = custrepos.findByCustnameContainingIgnoringCase(name);
        return list;
    }

    @Override
    public List<OrderCounts> getOrderCounts()
    {
        List<OrderCounts> list = custrepos.findOrderCounts();
        return list;
    }

    @Transactional
    @Override
    public void delete(Long custId)
    {
        if (custrepos.findById(custId).isPresent())
        {
            custrepos.deleteById(custId);
        } else {
            throw new EntityNotFoundException("Customer " + custId + " Not found!");
        }
    }

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();

        // primary key, id = 0 Add
        //                != 0 Update (delete / add)
        if(customer.getCustcode() != 0)
        {
         findCustomerById(customer.getCustcode());
         newCustomer.setCustcode(customer.getCustcode());
        }

        //set single fields

            newCustomer.setCustname(customer.getCustname());
            newCustomer.setCustcity(customer.getCustcity());
            newCustomer.setCustcountry(customer.getCustcountry());
            newCustomer.setGrade(customer.getGrade());
            newCustomer.setOpeningamt(customer.getOpeningamt());
            newCustomer.setOutstandingamt(customer.getOutstandingamt());
            newCustomer.setPaymentamt(customer.getPaymentamt());
            newCustomer.setPhone(customer.getPhone());
            newCustomer.setReceiveamt(customer.getReceiveamt());
            newCustomer.setWorkingarea(customer.getWorkingarea());
            newCustomer.setAgent(customer.getAgent());

        //customer must already exist to access its collection
            newCustomer.getOrders().clear();
            for (Order o : customer.getOrders()) {
                Order newOrder = ordrepos.findById(o.getOrdnum())
                        .orElseThrow(() -> new EntityNotFoundException("Order " + o.getOrdnum() + " cannot be found!"));
                newCustomer.getOrders().add(newOrder);
            }

        return custrepos.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer update(Customer customer, Long custcode)
    {
        Customer newCustomer = findCustomerById(custcode);

        //set single fields
        if (customer.getCustname() != null)
        {
            newCustomer.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null)
        {
            newCustomer.setCustcity(customer.getCustcity());
        }
        if (customer.getCustname() != null)
        {
            newCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getGrade() != null)
        {
            newCustomer.setGrade(customer.getGrade());
        }
        if (customer.getOpeningamt() != 0)
        {
            newCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if (customer.getOutstandingamt() != 0)
        {
            newCustomer.setOutstandingamt(customer.getOutstandingamt());
        }
        if (customer.getPaymentamt() != 0)
        {
            newCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if (customer.getPhone() != null)
        {
            newCustomer.setPhone(customer.getPhone());
        }
        if (customer.getReceiveamt() != 0)
        {
            newCustomer.setReceiveamt(customer.getReceiveamt());
        }
        if (customer.getWorkingarea() != null)
        {
            newCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getAgent() != null)
        {
            newCustomer.setAgent(customer.getAgent());
        }

        //if customer orders already exist, set
        if (customer.getOrders().size() > 0)
        {
            newCustomer.getOrders().clear();
            for (Order o : customer.getOrders())
            {
                Order newOrder = ordrepos.findById(o.getOrdnum())
                        .orElseThrow(() -> new EntityNotFoundException("Order " + o.getOrdnum() + " cannot be found!"));
                newCustomer.getOrders().add(newOrder);
            }
        }

        return custrepos.save(newCustomer);
    }
}
