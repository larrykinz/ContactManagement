package org.example.services.implementation;

import org.example.data.models.Contact;
import org.example.data.repository.ContactRepository;
import org.example.dto.request.*;
import org.example.dto.response.*;
import org.example.exception.ContactAlreadyExistException;
import org.example.exception.ContactDoesNotExistException;
import org.example.services.interfaces.ContactServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ContactImpl implements ContactServices {
    @Autowired
    private ContactRepository contactRepository;
    public AddContactResponse addContact(AddContactRequest addContactRequest) {
        for (Contact contact : contactRepository.findAll()) {
            if (contact.getEmail().equals(addContactRequest.getEmail())) {
                throw new ContactAlreadyExistException("Contact already exists with this email");
            }
        }
        Contact newContact = new Contact();
        newContact.setFirstName(addContactRequest.getFirstName());
        newContact.setLastName(addContactRequest.getLastName());
        newContact.setEmail(addContactRequest.getEmail());
        newContact.setPhoneNumber(addContactRequest.getPhoneNumber());
        contactRepository.save(newContact);

        AddContactResponse response = new AddContactResponse();
        response.setFirstName(newContact.getFirstName());
        response.setLastName(newContact.getLastName());
        response.setEmail(newContact.getEmail());
        response.setPhoneNumber(newContact.getPhoneNumber());
        return response;
    }

    public DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest) {
        for (Contact contact : contactRepository.findAll()) {
            if (contact.getId().equals(deleteContactRequest.getContactId())) {
                contactRepository.delete(contact);

                DeleteContactResponse deleteContactResponse = new DeleteContactResponse();
                deleteContactResponse.setContactId(deleteContactRequest.getContactId());
                deleteContactResponse.setMessage("Contact successfully removed.");

                return deleteContactResponse;
            }
        }

        throw new ContactDoesNotExistException("Contact does not exist with this ID.");
    }

    public UpdateContactResponse updateContact(UpdateContactRequest updateContactRequest) {
        Contact contactToUpdate = null;
        for (Contact contact : contactRepository.findAll()) {
            if (contact.getEmail().equals(updateContactRequest.getEmail())) {
                contactToUpdate = contact;
                contactToUpdate.setFirstName(updateContactRequest.getFirstName());
                contactToUpdate.setLastName(updateContactRequest.getLastName());
                contactToUpdate.setEmail(updateContactRequest.getEmail());
                contactToUpdate.setPhoneNumber(updateContactRequest.getPhoneNumber());
                contactRepository.save(contactToUpdate);
                break;
            }
        }

        if (contactToUpdate == null) {
            throw new ContactDoesNotExistException("Contact does not exist with this Email.");
        }
        UpdateContactResponse updateContactResponse = new UpdateContactResponse();

        updateContactResponse.setFirstName(contactToUpdate.getFirstName());
        updateContactResponse.setLastName(contactToUpdate.getLastName());
        updateContactResponse.setEmail(contactToUpdate.getEmail());
        updateContactResponse.setPhoneNumber(contactToUpdate.getPhoneNumber());
        updateContactResponse.setMessage("contact successfully updated.");


        return updateContactResponse;
    }

    public SearchContactsResponse searchAllContacts(SearchContactsRequest findAllContactsRequest) {
        List<Contact> contacts = contactRepository.findAll();
        SearchContactsResponse searchContactsResponse = new SearchContactsResponse();

        searchContactsResponse.setContacts(contacts);
        searchContactsResponse.setMessage(" ALL Contacts Found");

        return searchContactsResponse;
    }

    public SearchContactsByPhoneNumberResponse searchContactsByPhoneNumber(SearchContactsByPhoneNumberRequest request) {
        List<Contact> contacts = contactRepository.findAll();
        List<Contact> matchedContacts = new ArrayList<>();

        String searchPhoneNumber = request.getPhoneNumber();
        for (Contact contact : contacts) {
            if (searchPhoneNumber != null && searchPhoneNumber.equals(contact.getPhoneNumber())) {
                matchedContacts.add(contact);
            }
        }
        SearchContactsByPhoneNumberResponse searchContactsResponse = new SearchContactsByPhoneNumberResponse();
        searchContactsResponse.setContacts(matchedContacts);
        searchContactsResponse.setMessage("Phone number Found");

        return searchContactsResponse;
    }



}
