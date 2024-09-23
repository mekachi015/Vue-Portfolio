<template>
    <div class="form-container">
  <h2>Contact Form</h2>
  <form @submit.prevent="handleSubmit" class="contact-form">
    <div class="form-group">
      <label for="firstName">First Name</label>
      <input type="text" id="firstName" v-model="formData.firstName" required>
    </div>

    <div class="form-group">
      <label for="lastName">Last Name</label>
      <input type="text" id="lastName" v-model="formData.lastName" required>
    </div>

    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" id="email" v-model="formData.email" required>
    </div>

    <div class="form-group">
      <label for="phone">Phone Number</label>
      <input type="tel" id="phone" v-model="formData.phone" required>
    </div>

    <div class="form-buttons">
      <button type="submit" class="btn-submit">Submit</button>
      <button type="button" class="btn-clear" @click="clearForm">Clear Fields</button>
    </div>
  </form>
</div>

</template>

<script>
export default {

    name: 'ContactPage',

    data() {
        return {
            formData: {
                firstName: '',
                lastName: '',
                email: '',
                phone: ''
            }
        };
    },
    methods: {
        handleSubmit() {
            // handle form submission logic
            console.log(this.formData);
            alert('Form Submitted!');
        },
        clearForm() {
            this.formData = {
                firstName: '',
                lastName: '',
                email: '',
                phone: ''
            };
        }
    }
};
</script>


<style scoped>
.form-container {
  width: 100%;
  max-width: 500px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f4f4f4;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  font-family: 'Poppins', sans-serif;
}

h2 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.contact-form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

label {
  font-size: 1rem;
  margin-bottom: 5px;
}

input[type="text"],
input[type="email"],
input[type="tel"] {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1rem;
}

input:focus {
  outline: none;
  border-color: #3498db;
  box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
}

.form-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  cursor: pointer;
}

.btn-submit {
  background-color: #3498db;
  color: white;
}

.btn-clear {
  background-color: #e74c3c;
  color: white;
}

button:hover {
  opacity: 0.8;
}
</style>
