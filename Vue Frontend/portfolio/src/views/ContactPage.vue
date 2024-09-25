<template>
  <div class="form-container">
    <h2>Contact Form</h2>
    <form @submit.prevent="handleSubmit()" class="contact-form">
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

      <div class="form-group">
        <label for="query">Query</label>
        <input type="text" id="query" v-model="formData.query" required>
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
        phone: '',
        query: ''  // Added the query field here
      }
    };
  },
  methods: {
    handleSubmit() {
      // Sending form data to Formspree
      fetch("https://formspree.io/f/mnnakkdg", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.formData), // Updated to use formData
      })
        .then((response) => response.json())
        .then((data) => {
          console.log("Success:", data);
          if (data.ok) {
            alert("Form submitted successfully!");
            this.clearForm(); // Clear form only on success
          } else {
            alert("There was an error submitting the form. Please try again.");
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          alert("There was an error submitting the form.");
        });
    },
    clearForm() {
      this.formData = {
        firstName: '',
        lastName: '',
        email: '',
        phone: '',
        query: ''  // Reset query field
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
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  font-family: 'Poppins', sans-serif;
}

h2 {
  text-align: center;
  color: var(--primary-color);
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
  background-color: var(--primary-color);
  color: white;
}

.btn-clear {
  background-color: var(--secondary-color);
  color: white;
}

button:hover {
  opacity: 0.8;
}
</style>
