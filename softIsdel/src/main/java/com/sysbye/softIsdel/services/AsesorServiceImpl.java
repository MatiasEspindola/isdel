//package com.sysbye.softIsdel.services;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.sysbye.softIsdel.models.entities.Asesor;
//import com.sysbye.softIsdel.repo.IAsesorRepo;
//
////@Service
//public class AsesorServiceImpl implements IAsesorService {
//
////	@Autowired
////	IAsesorRepo asesorRepo;
////        
//////        @Override
//////        @Transactional(readOnly = true)
//////        public List<Asesor> buscarPorTerm(String term){
//////            return asesorRepo.buscarPorTerm(term);
//////        }
////
////	@Override
////	@Transactional(readOnly = true)
////	public List<Asesor> findAll() {
////
////		return (List<Asesor>) asesorRepo.findAll();
////	}
////
////	@Override
////	@Transactional
////	public void save(Asesor asesor) {
////
////		asesorRepo.save(asesor);
////	}
////
////	@Override
////	public Asesor findOne(Long id) {
////		
////		return asesorRepo.findById(id).orElse(null);
////	}
////
////	@Override
////	public void delete(Long id) {
////		asesorRepo.deleteById(id);
////	}
//
//}
