import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { listEmployees, deleteEmployee  } from './EmployeeService'



const ListEmployeeComponent = () => {

    const [employees, setEmployees] = useState([]);
    const navigate = useNavigate();


    useEffect(() => {
        getAllEmployees();
    }, [])

    const getAllEmployees = () => {
        listEmployees().then((response) => {
            setEmployees(response.data)
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }

    const removeEmployee = (employeeId) => {
        deleteEmployee(employeeId).then((response) => {
            getAllEmployees();

        }).catch(error => {
            console.log(error);
        })

    }

    const addNewEmployee = () => {
    navigate('/add-employee');
    };

    const updateEmployee = (id) => {
        navigate(`/edit-employee/${id}`)
    }

    return (
        <div className="container">
            <h2 className="text-center"> List Employees </h2>
            {/* <Link to = "/add-employee" className = "btn btn-primary mb-2" > Add Employee </Link> */}
            <button className = "btn btn-success mb-2" onClick={addNewEmployee }>Add Employee</button>
            <table className="table table-bordered table-striped">
                <thead>
                    <th> Employee Id </th>
                    <th> Employee First Name </th>
                    <th> Employee Last Name </th>
                    <th> Employee Email Id </th>
                    <th> Actions </th>
                </thead>
                <tbody>
                    {
                        employees.map(
                            employee =>
                                <tr key={employee.id}>
                                    <td> {employee.id} </td>
                                    <td> {employee.firstName} </td>
                                    <td>{employee.lastName}</td>
                                    <td>{employee.email}</td>
                                    <td>
                                    <button className="btn btn-info" onClick={() => updateEmployee(employee.id)} >Update</button>
                                    <button className = "btn btn-danger" onClick = {() => removeEmployee(employee.id)}
                                    style = {{marginLeft:"10px"}}> Delete</button>

                                </td>
                                </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListEmployeeComponent;
