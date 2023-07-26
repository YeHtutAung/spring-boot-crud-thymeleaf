package com.yha.springbootscrudthymeleaf.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yha.springbootscrudthymeleaf.models.Model1;
import com.yha.springbootscrudthymeleaf.repository.IModel1Repository;

@Service
public class Model1Service {

	@Autowired
	IModel1Repository model1Repository;

	public List<Model1> getAll() {

		List<Model1> results = model1Repository.findAll();

		if (results.size() > 0) {
			return results;
		} else {
			return new ArrayList<Model1>();
		}
	}

	public Model1 getModel1ById(Long id) {
		Model1 model1 = model1Repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		if (model1 != null) {
			return model1;
		} else {
			return new Model1();
		}
	}

	public Model1 saveModel1(Model1 model1) {
		Model1 result = model1Repository.save(model1);

		if (result != null) {
			return result;
		} else {
			return new Model1();
		}
	}

	public void deleteModel1(Model1 model1) {
		model1Repository.delete(model1);
	}
}
