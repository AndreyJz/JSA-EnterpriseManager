import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { OptionType, FormDataType } from '../types';
import { useNavigate } from 'react-router-dom'; 

export default function Component() {
  const [formData, setFormData] = useState<FormDataType>({
    id: '',
    name: '',
    lastname: '',
    username: '',
    emails: [{ email: '', emailType: '' }],
    password: '',
    repeatedPassword: '',
    personType: '',
    phones: [{ phone: '', phoneType: '' }],
  });

  const [emailOptions, setEmailOptions] = useState<OptionType[]>([]);
  const [phoneOptions, setPhoneOptions] = useState<OptionType[]>([]);
  const [personTypeOptions, setPersonTypeOptions] = useState<OptionType[]>([]);
  const [isSubmitDisabled, setIsSubmitDisabled] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchOptions = async () => {
      try {
        const [emailRes, phoneRes, personTypeRes] = await Promise.all([
          fetch('http://localhost:8081/api/Email_Type'),
          fetch('http://localhost:8081/api/Phone_Type'),
          fetch('http://localhost:8081/api/Person_Type')
        ]);

        const [emailData, phoneData, personTypeData] = await Promise.all([
          emailRes.json(),
          phoneRes.json(),
          personTypeRes.json()
        ]);

        setEmailOptions(emailData);
        setPhoneOptions(phoneData);
        setPersonTypeOptions(personTypeData);
      } catch (error) {
        console.error('Error fetching options:', error);
      }
    };

    fetchOptions();
  }, []);

  useEffect(() => {
    const isFormValid = () => {
      const requiredFields: (keyof FormDataType)[] = ['id', 'name', 'lastname', 'username', 'password', 'repeatedPassword', 'personType'];
      const isFilled = requiredFields.every(field => formData[field] !== '');
      const areEmailsFilled = formData.emails.every(emailObj => emailObj.email !== '' && emailObj.emailType !== '');
      const arePhonesFilled = formData.phones.every(phoneObj => phoneObj.phone !== '' && phoneObj.phoneType !== '');
      return isFilled && areEmailsFilled && arePhonesFilled && (formData.password === formData.repeatedPassword);
    };

    setIsSubmitDisabled(!isFormValid());
  }, [formData]);

  const handleChange = (e: { target: { name: any; value: any; }; }) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleEmailChange = (index: number, field: string, value: string) => {
    setFormData(prev => ({
      ...prev,
      emails: prev.emails.map((email, i) => 
        i === index ? { ...email, [field]: value } : email
      ),
    }));
  };

  const handlePhoneChange = (index: number, field: string, value: string) => {
    setFormData(prev => ({
      ...prev,
      phones: prev.phones.map((phone, i) => 
        i === index ? { ...phone, [field]: value } : phone
      ),
    }));
  };

  const addEmailField = () => {
    setFormData(prev => ({
      ...prev,
      emails: [...prev.emails, { email: '', emailType: '' }],
    }));
  };

  const addPhoneField = () => {
    setFormData(prev => ({
      ...prev,
      phones: [...prev.phones, { phone: '', phoneType: '' }],
    }));
  };

  const removeEmailField = (index: number) => {
    if (formData.emails.length > 1) {
      setFormData(prev => ({
        ...prev,
        emails: prev.emails.filter((_, i) => i !== index),
      }));
    }
  };

  const removePhoneField = (index: number) => {
    if (formData.phones.length > 1) {
      setFormData(prev => ({
        ...prev,
        phones: prev.phones.filter((_, i) => i !== index),
      }));
    }
  };

  const handleSubmit = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();

    try {
      // Send person data
      const personResponse = await fetch('http://localhost:8081/api/Person/customer', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          id: formData.id,
          name: formData.name,
          lastname: formData.lastname,
          username: formData.username,
          password: formData.password,
          repeatedPassword: formData.repeatedPassword,
          personType: {
            id: parseInt(formData.personType)
          },
          branch: {
            id: 1
          }
        }),
      });

      if (!personResponse.ok) throw new Error('Failed to create person');

      // Send emails
      const emailPromises = formData.emails.map(email =>
        fetch('http://localhost:8081/api/Email', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            mail: email.email,
            emailType: {
              id: parseInt(email.emailType)
            },
            person: {
              id: formData.id
            }
          }),
        })
      );

      // Send phones
      const phonePromises = formData.phones.map(phone =>
        fetch('http://localhost:8081/api/Phone', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            number: phone.phone,
            phoneType: {
              id: parseInt(phone.phoneType)
            },
            person: {
              id: formData.id
            }
          }),
        })
      );

      await Promise.all([...emailPromises, ...phonePromises]);
      
      alert('Registration successful!');
      navigate('/login');
      
    } catch (error) {
      console.error('Error submitting form:', error);
      alert('Error during registration. Please try again.');
    }
  };

  return (
    <div className="bg-gray-100 min-h-screen py-16">
      <div className="container mx-auto px-4">
        <div className="max-w-4xl mx-auto bg-white p-8 rounded-lg shadow-md">
          <h2 className="text-3xl font-bold text-center mb-6">Sign Up</h2>
          <form onSubmit={handleSubmit} className="space-y-6">
            {/* ID and Person Type row */}
            <div className="grid grid-cols-2 gap-4">
              <div>
                <label htmlFor="id" className="block text-gray-700 font-bold mb-2">ID</label>
                <input
                  type="text"
                  id="id"
                  name="id"
                  value={formData.id}
                  onChange={handleChange}
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                  required
                />
              </div>
              <div>
                <label htmlFor="personType" className="block text-gray-700 font-bold mb-2">Person Type</label>
                <select
                  id="personType"
                  name="personType"
                  value={formData.personType}
                  onChange={handleChange}
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                  required
                >
                  <option value="">Select Person Type</option>
                  {personTypeOptions.map((option) => (
                    <option key={option.id} value={option.id}>
                      {`${option.id}.${option.name}`}
                    </option>
                  ))}
                </select>
              </div>
            </div>

            {/* Main form grid */}
            <div className="grid md:grid-cols-2 gap-6">
              <div className="space-y-4">
                <div>
                  <label htmlFor="name" className="block text-gray-700 font-bold mb-2">Name</label>
                  <input
                    type="text"
                    id="name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                  />
                </div>
                <div>
                  <label htmlFor="lastname" className="block text-gray-700 font-bold mb-2">Lastname</label>
                  <input
                    type="text"
                    id="lastname"
                    name="lastname"
                    value={formData.lastname}
                    onChange={handleChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                  />
                </div>
                <div>
                  <label htmlFor="username" className="block text-gray-700 font-bold mb-2">Username</label>
                  <input
                    type="text"
                    id="username"
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                  />
                </div>
                <div>
                  <label htmlFor="password" className="block text-gray-700 font-bold mb-2">Password</label>
                  <input
                    type="password"
                    id="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                  />
                </div>
                <div>
                  <label htmlFor="confirmPassword" className="block text-gray-700 font-bold mb-2">Confirm Password</label>
                  <input
                    type="password"
                    id="confirmPassword"
                    name="repeatedPassword"
                    value={formData.repeatedPassword}
                    onChange={handleChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                  />
                </div>
              </div>

              <div className="space-y-4">
                {/* Emails Section */}
                <div className="space-y-4">
                  <h3 className="font-bold text-gray-700">Email Addresses</h3>
                  {formData.emails.map((emailObj, index) => (
                    <div key={index} className="space-y-2">
                      <div className="flex gap-2">
                        <input
                          type="email"
                          value={emailObj.email}
                          onChange={(e) => handleEmailChange(index, 'email', e.target.value)}
                          className="flex-1 px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                          placeholder="Email address"
                          required
                        />
                        <select
                          value={emailObj.emailType}
                          onChange={(e) => handleEmailChange(index, 'emailType', e.target.value)}
                          className="w-1/3 px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                          required
                        >
                          <option value="">Type</option>
                          {emailOptions.map((option) => (
                            <option key={option.id} value={option.id}>
                              {`${option.id}.${option.name}`}
                            </option>
                          ))}
                        </select>
                        {formData.emails.length > 1 && (
                          <button
                            type="button"
                            onClick={() => removeEmailField(index)}
                            className="px-2 py-1 text-red-500 hover:text-red-700"
                          >
                            ×
                          </button>
                        )}
                      </div>
                    </div>
                  ))}
                  <button
                    type="button"
                    onClick={addEmailField}
                    className="text-sm text-blue-600 hover:text-blue-800"
                  >
                    + Add another email
                  </button>
                </div>

                {/* Phones Section */}
                <div className="space-y-4">
                  <h3 className="font-bold text-gray-700">Phone Numbers</h3>
                  {formData.phones.map((phoneObj, index) => (
                    <div key={index} className="space-y-2">
                      <div className="flex gap-2">
                        <input
                          type="tel"
                          value={phoneObj.phone}
                          onChange={(e) => handlePhoneChange(index, 'phone', e.target.value)}
                          className="flex-1 px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                          placeholder="Phone number"
                          required
                        />
                        <select
                          value={phoneObj.phoneType}
                          onChange={(e) => handlePhoneChange(index, 'phoneType', e.target.value)}
                          className="w-1/3 px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                          required
                        >
                          <option value="">Type</option>
                          {phoneOptions.map((option) => (
                            <option key={option.id} value={option.id}>
                              {`${option.id}.${option.name}`}
                            </option>
                          ))}
                        </select>
                        {formData.phones.length > 1 && (
                          <button
                            type="button"
                            onClick={() => removePhoneField(index)}
                            className="px-2 py-1 text-red-500 hover:text-red-700"
                          >
                            ×
                          </button>
                        )}
                      </div>
                    </div>
                  ))}
                  <button
                    type="button"
                    onClick={addPhoneField}
                    className="text-sm text-blue-600 hover:text-blue-800"
                  >
                    + Add another phone
                  </button>
                </div>
              </div>
            </div>

            {/* Submit Button */}
            <div className="mt-6">
              <button
                type="submit"
                className={`w-full bg-blue-500 text-white font-bold py-3 px-4 rounded-md transition-colors ${
                  isSubmitDisabled
                    ? 'opacity-50 cursor-not-allowed'
                    : 'hover:bg-blue-600'
                }`}
                disabled={isSubmitDisabled}
              >
                Create Account
              </button>
            </div>
          </form>

          <div className="flex justify-between mt-6 text-sm">
            <p className="text-gray-600">Already have an account?</p>
            <Link to="/login" className="text-blue-500 hover:text-blue-700 font-medium">
              Log In →
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}