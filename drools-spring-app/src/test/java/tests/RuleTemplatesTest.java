package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.junit.Test;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import sbnz.integracija.example.model.Car;
import sbnz.integracija.example.model.Reservation;
import sbnz.integracija.example.model.User;

public class RuleTemplatesTest {

	@Test
	public void testSimpleTemplateWithArrays() {

		File initialFile = new File(System.getProperty("user.dir").replace("drools-spring-app", "drools-spring-kjar")
				+ "\\src\\main\\resources\\sbnz\\integracija\\depozit.drt");

		InputStream template = null;
		try {
			template = new FileInputStream(initialFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		DataProvider dataProvider = new ArrayDataProvider(
				new String[][] { new String[] { "500", "1000", "10" }, new String[] { "1001", "3000", "20" },
						new String[] { "3001", "7000", "30" }, new String[] { "7001", "10001", "40" },

				});

		DataProviderCompiler converter = new DataProviderCompiler();
		String drl = converter.compile(dataProvider, template);

		System.out.println(drl);

		KieSession ksession = createKieSessionFromDRL(drl);

		doTest(ksession);
	}

	@SuppressWarnings("deprecation")
	private void doTest(KieSession ksession) {
		Date d = new Date();

		User u = new User();
		u.setId(1);
		Car c = new Car();
		c.setId(1);
		d.setMonth(10);
		Reservation r1 = new Reservation(d, d, "REZERVISAN", 600);
		r1.setCar(c);
		r1.setUser(u);
		r1.setDiscount(0);
		d = new Date();
		d.setMonth(5);
		User u2 = new User();
		u2.setId(2);
		Reservation r2 = new Reservation(d, d, "REZERVISAN", 2000);
		r2.setCar(c);
		r2.setUser(u2);
		Car cc = new Car();
		cc.setId(100);
		Reservation r3 = new Reservation(d, d, "REZERVISAN", 5000);
		r3.setCar(cc);
		r3.setUser(u2);
		d = new Date();
		User u3 = new User();
		u3.setId(3);

		Reservation r4 = new Reservation(d, d, "REZERVISAN", 8500);
		r4.setCar(c);
		r4.setUser(u3);
		r4.setDiscount(0);

		ksession.insert(r1);
		ksession.insert(r2);
		ksession.insert(r3);
		ksession.insert(r4);

		ksession.fireAllRules();

		assertEquals("10%", r1.getDeposit());
		assertEquals("20%", r2.getDeposit());
		assertEquals("30%", r3.getDeposit());
		assertEquals("40%", r4.getDeposit());
	}

	private KieSession createKieSessionFromDRL(String drl) {
		KieHelper kieHelper = new KieHelper();
		kieHelper.addContent(drl, ResourceType.DRL);

		Results results = kieHelper.verify();

		if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
			List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
			for (Message message : messages) {
				System.out.println("Error: " + message.getText());
			}

			throw new IllegalStateException("Compilation errors were found. Check the logs.");
		}

		return kieHelper.build().newKieSession();
	}
}
