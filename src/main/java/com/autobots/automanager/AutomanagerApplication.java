package com.autobots.automanager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.UserPasswordCredential;
import com.autobots.automanager.entity.Document;
import com.autobots.automanager.entity.Email;
import com.autobots.automanager.entity.Company;
import com.autobots.automanager.entity.Address;
import com.autobots.automanager.entity.Merchandise;
import com.autobots.automanager.entity.ServiceEntity;
import com.autobots.automanager.entity.Telephone;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Vehicle;
import com.autobots.automanager.entity.Sale;
import com.autobots.automanager.enumerators.UserProfile;
import com.autobots.automanager.enumerators.DocumentType;
import com.autobots.automanager.enumerators.VehicleType;
import com.autobots.automanager.repository.CompanyRepository;
import com.autobots.automanager.repository.UserRepository;
import com.autobots.automanager.services.CompanyService;

@SpringBootApplication
public class AutomanagerApplication implements CommandLineRunner {

	@Autowired
	private CompanyService service;

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

			Company company = new Company();
			company.setSocialReason("Car service toyota ltda");
			company.setFantasyName("Car service manutenção veicular");
			company.setRegistrationDate(new Date());

			Address companyAddress = new Address();
			companyAddress.setState("São Paulo");
			companyAddress.setCity("São Paulo");
			companyAddress.setDistrict("Centro");
			companyAddress.setStreet("Av. São João");
			companyAddress.setNumber("00");
			companyAddress.setZipCode("01035-000");

			company.setAddress(companyAddress);

			Telephone companyTelephone = new Telephone();
			companyTelephone.setDdd("011");
			companyTelephone.setNumber("986454527");

			company.getTelephones().add(companyTelephone);

			User employee = new User();
			employee.setName("Pedro Alcântara de Bragança e Bourbon");
			employee.setSocialName("Dom Pedro");
			employee.getProfiles().add(UserProfile.EMPLOYEE);

			Email employeeEmail = new Email();
			employeeEmail.setAddress("a@a.com");

			employee.getEmails().add(employeeEmail);

			Address employeeAddress = new Address();
			employeeAddress.setState("São Paulo");
			employeeAddress.setCity("São Paulo");
			employeeAddress.setDistrict("Jardins");
			employeeAddress.setStreet("Av. São Gabriel");
			employeeAddress.setNumber("00");
			employeeAddress.setZipCode("01435-001");

			employee.setAddress(employeeAddress);

			company.getUsers().add(employee);

			Telephone employeeTelephone = new Telephone();
			employeeTelephone.setDdd("011");
			employeeTelephone.setNumber("9854633728");

			employee.getTelephones().add(employeeTelephone);

			Document cpf = new Document();
			cpf.setEmissionDate(new Date());
			cpf.setNumber("856473819229");
			cpf.setDocumentType(DocumentType.CPF);

			employee.getDocuments().add(cpf);

			UserPasswordCredential employeeCredentials = new UserPasswordCredential();
			employeeCredentials.setInactive(false);
			employeeCredentials.setUsername("dompedrofuncionario");
			employeeCredentials.setPassword("123456");
			employeeCredentials.setCreationDate(new Date());
			employeeCredentials.setLastAccess(new Date());

			employee.getCredentials().add(employeeCredentials);

			User provider = new User();
			provider.setName("Componentes varejo de partes automotivas ltda");
			provider.setSocialName("Loja do carro, vendas de componentes automotivos");
			provider.getProfiles().add(UserProfile.PROVIDER);

			Email providerEmail = new Email();
			providerEmail.setAddress("f@f.com");

			provider.getEmails().add(providerEmail);

			UserPasswordCredential providerCredentials = new UserPasswordCredential();
			providerCredentials.setInactive(false);
			providerCredentials.setUsername("dompedrofornecedor");
			providerCredentials.setPassword("123456");
			providerCredentials.setCreationDate(new Date());
			providerCredentials.setLastAccess(new Date());

			provider.getCredentials().add(providerCredentials);

			Document cnpj = new Document();
			cnpj.setEmissionDate(new Date());
			cnpj.setNumber("00014556000100");
			cnpj.setDocumentType(DocumentType.CNPJ);

			provider.getDocuments().add(cnpj);

			Address providerAddress= new Address();
			providerAddress.setState("Rio de Janeiro");
			providerAddress.setCity("Rio de Janeiro");
			providerAddress.setDistrict("Centro");
			providerAddress.setStreet("Av. República do chile");
			providerAddress.setNumber("00");
			providerAddress.setZipCode("20031-170");

			provider.setAddress(providerAddress);

			company.getUsers().add(provider);
			
			Merchandise rodaLigaLeve = new Merchandise();
			rodaLigaLeve.setRegistration(new Date());
			rodaLigaLeve.setManufacturing(new Date());
			rodaLigaLeve.setName("Roda de liga leva modelo toyota etios");
			rodaLigaLeve.setExpiration(new Date());
			rodaLigaLeve.setQuantity(30);
			rodaLigaLeve.setValue(300.0);
			rodaLigaLeve.setDescription("Roda de liga leve original de fábrica da toyta para modelos do tipo hatch");

			company.getMerchandises().add(rodaLigaLeve);

			provider.getMerchandise().add(rodaLigaLeve);

			User client = new User();
			client.setName("Pedro Alcântara de Bragança e Bourbon");
			client.setSocialName("Dom pedro cliente");
			client.getProfiles().add(UserProfile.CLIENT);

			Email clientEmail = new Email();
			clientEmail.setAddress("c@c.com");

			client.getEmails().add(clientEmail);

			Document clientCpf = new Document();
			clientCpf.setEmissionDate(new Date());
			clientCpf.setNumber("12584698533");
			clientCpf.setDocumentType(DocumentType.CPF);

			client.getDocuments().add(clientCpf);

			UserPasswordCredential clientCredentials = new UserPasswordCredential();
			clientCredentials.setInactive(false);
			clientCredentials.setUsername("dompedrocliente");
			clientCredentials.setPassword("123456");
			clientCredentials.setCreationDate(new Date());
			clientCredentials.setLastAccess(new Date());

			client.getCredentials().add(clientCredentials);

			Address clientAddress = new Address();
			clientAddress.setState("São Paulo");
			clientAddress.setCity("São José dos Campos");
			clientAddress.setDistrict("Centro");
			clientAddress.setStreet("Av. Dr. Nelson D'Ávila");
			clientAddress.setNumber("00");
			clientAddress.setZipCode("12245-070");

			client.setAddress(clientAddress);
			
			Vehicle vehicle = new Vehicle();
			vehicle.setLicensePlate("ABC-0000");
			vehicle.setModel("corolla-cross");
			vehicle.setVehicleType(VehicleType.SUV);
			vehicle.setOwner(client);
			
			client.getVehicles().add(vehicle);
			
			company.getUsers().add(client);

			ServiceEntity trocaRodas = new ServiceEntity();
			trocaRodas.setDescription("Troca das rodas do carro por novas");
			trocaRodas.setName("Troca de rodas");
			trocaRodas.setValue(50);

			ServiceEntity alinhamento = new ServiceEntity();
			alinhamento.setDescription("Alinhamento das rodas do carro");
			alinhamento.setName("Alinhamento de rodas");
			alinhamento.setValue(50);

			company.getServices().add(trocaRodas);
			company.getServices().add(alinhamento);

			Sale venda = new Sale();
			venda.setRegistrationDate(new Date());
			venda.setClient(client);
			venda.getMerchandises().add(rodaLigaLeve);
			venda.setIdentification("1234698745");
			venda.setEmployee(employee);
			venda.getServices().add(trocaRodas);
			venda.getServices().add(alinhamento);
			venda.setVehicle(vehicle);
			vehicle.getSales().add(venda);

			company.getSales().add(venda);

			service.insert(company);
			
			Merchandise rodaLigaLeve2 = new Merchandise();
			rodaLigaLeve2.setRegistration(new Date());
			rodaLigaLeve2.setManufacturing(new Date());
			rodaLigaLeve2.setName("Roda de liga leva modelo toyota etios");
			rodaLigaLeve2.setExpiration(new Date());
			rodaLigaLeve2.setQuantity(30);
			rodaLigaLeve2.setValue(300.0);
			rodaLigaLeve2.setDescription("Roda de liga leve original de fábrica da toyta para modelos do tipo hatch");
			
			ServiceEntity alinhamento2 = new ServiceEntity();
			alinhamento2.setDescription("Alinhamento das rodas do carro");
			alinhamento2.setName("Alinhamento de rodas");
			alinhamento2.setValue(50);
			
			ServiceEntity balanceamento = new ServiceEntity();
			balanceamento.setDescription("balanceamento das rodas do carro");
			balanceamento.setName("balanceamento de rodas");
			balanceamento.setValue(30);
			
			Sale venda2 = new Sale();
			venda2.setRegistrationDate(new Date());
			venda2.setClient(client);
			venda2.getMerchandises().add(rodaLigaLeve2);
			venda2.setIdentification("1234698749");
			venda2.setEmployee(employee);
			venda2.getServices().add(balanceamento);
			venda2.getServices().add(alinhamento2);
			venda2.setVehicle(vehicle);
			vehicle.getSales().add(venda2);

			company.getSales().add(venda2);
			
			service.insert(company);

		}

	}

