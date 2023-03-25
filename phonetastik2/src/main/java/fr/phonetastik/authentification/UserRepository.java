package fr.phonetastik.authentification;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {}