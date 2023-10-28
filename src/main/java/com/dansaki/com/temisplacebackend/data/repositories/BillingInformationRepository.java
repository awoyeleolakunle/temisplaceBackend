package com.dansaki.com.temisplacebackend.data.repositories;


import com.dansaki.com.temisplacebackend.data.models.BillingInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingInformationRepository extends JpaRepository<BillingInformation, Long> {

}
