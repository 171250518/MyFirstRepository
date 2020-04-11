package nju.edu.cinemaSystem.blImpl.management.employee;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class EmployeeServiceImplTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(EmployeeServiceImpl.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @org.junit.Test
    public void addEmployee() {
    }

    @org.junit.Test
    public void deleteBatchOfEmployee() {
    }

    @org.junit.Test
    public void updateEmployee() {
    }

    @org.junit.Test
    public void getEmployees() {
    }

    @org.junit.Test
    public void getEmployeeById() {
    }
}
