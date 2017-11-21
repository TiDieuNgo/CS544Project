package mum.cs544.project.service;

import org.springframework.beans.factory.annotation.Autowired;

import mum.cs544.project.entity.Appointment;
import mum.cs544.project.entity.Session;
import mum.cs544.project.repository.AppointmentRepository;
import mum.cs544.project.repository.SessionRepository;

public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public boolean createAppointment(Appointment appt) {
		Session session = appt.getSession();
		session.setCapacity(session.getCapacity() - 1);

		if (appointmentRepository.save(appt) != null || sessionRepository.save(session) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAppointment(Appointment appt) {
		Session session = appt.getSession();
		session.setCapacity(session.getCapacity() + 1);

		appointmentRepository.delete(appt);
		sessionRepository.delete(session);
		return false;
	}

}
