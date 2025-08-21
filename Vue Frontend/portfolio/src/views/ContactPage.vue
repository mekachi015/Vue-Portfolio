<template>
  <div class="contact-dashboard">
    <h2 class="section-header">Get In Touch</h2>
    <div class="contact-container">
      <div class="contact-info">
        <h3>Let's Connect</h3>
        <p>
          Feel free to reach out for collaborations, opportunities, or just to
          say hello!
        </p>
        <div class="contact-links">
          <a href="mailto:katlegomakoti07@gmail.com" class="contact-link">
            <i class="fas fa-envelope"></i>
            <span>Email Me</span>
          </a>
          <a
            href="https://linkedin.com/in/katlego-makoti-7802a7126/"
            target="_blank"
            class="contact-link"
          >
            <i class="fab fa-linkedin"></i>
            <span>LinkedIn</span>
          </a>
          <a
            href="https://github.com/mekachi015"
            target="_blank"
            class="contact-link"
          >
            <i class="fab fa-github"></i>
            <span>GitHub</span>
          </a>
        </div>
      </div>

      <div class="form-container">
        <form @submit.prevent="handleSubmit()" class="contact-form">
          <div class="form-grid">
            <div class="form-group">
              <label for="first_Name">First Name</label>
              <input
                type="text"
                id="first_Name"
                v-model="formData.firstName"
                required
              />
            </div>

            <div class="form-group">
              <label for="last_Name">Last Name</label>
              <input
                type="text"
                id="last_Name"
                v-model="formData.lastName"
                required
              />
            </div>

            <div class="form-group">
              <label for="email">Email</label>
              <input
                type="email"
                id="email"
                v-model="formData.email"
                required
              />
            </div>

            <div class="form-group">
              <label for="phone">Phone Number</label>
              <input type="tel" id="phone" v-model="formData.phone" required />
            </div>

            <div class="form-group full-width">
              <label for="query">Your Message</label>
              <textarea
                id="query"
                v-model="formData.query"
                required
                rows="4"
              ></textarea>
            </div>
          </div>

          <div class="form-buttons">
            <button type="submit" class="btn-submit">Send Message</button>
            <button type="button" class="btn-clear" @click="clearForm">
              Clear
            </button>
          </div>
        </form>
      </div>
    </div>
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
      },
      errors: {},
      loading: false,
      successMessage: '',
      errorMessage: ''
    };
  },
  methods: {
    /**handleSubmit() {
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
    }, **/

  async handleSubmit() {
    // Reset messages and errors
    this.errors = [];
    this.successMessage = '';
    this.errorMessage = '';
    this.loading = true;

    try {
      // Send data to the backend
      const baseUrl = "api/form/submit";
      const response = await fetch(baseUrl, {
        method: 'POST',
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.formData),
      });

      if (!response.ok ){
        console.error("Network response was not ok", response.statusText);
      }

      // Parse the JSON data from the response
      const data = await response.json();

      if (response.ok) {
        this.successMessage = "Form Submitted Successfully!";
        this.clearForm();
      } else {
        // Handle validation or other errors from the backend
        if (response.status === 400 || response.status === 409) {
          this.errors = data;
        } else {
          this.errorMessage = data.error || "There was an error submitting the form.";
        }
      }
    } catch (error) {
      // Handle network errors or other exceptions
      console.error("Error:", error);
      this.errorMessage = "There was an error submitting the form.";
    } finally {
      // This block always runs, regardless of success or failure
      this.loading = false;
    }
  },

  clearForm() {
    this.formData = {
      firstName: '',
      lastName: '',
      email: '',
      phone: '',
      query: '' // Reset query field
    };
  }
  },
};
</script>

<style scoped>
.contact-dashboard {
  min-height: 100vh;
  padding: 4rem 2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 3rem;
}

.section-header {
  font-size: 3rem;
  font-weight: 700;
  color: var(--primary-color);
  text-align: center;
  margin-bottom: 1rem;
  animation: fadeIn 0.8s ease;
}

.contact-container {
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 3rem;
  width: 100%;
  max-width: 1200px;
  animation: fadeInUp 0.5s ease;
}

.contact-info {
  padding: 2rem;
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
}

.contact-info h3 {
  font-size: 1.8rem;
  color: var(--primary-color);
  margin-bottom: 1rem;
}

.contact-info p {
  color: #666;
  line-height: 1.6;
  margin-bottom: 2rem;
}

.contact-links {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.contact-link {
  display: flex;
  align-items: center;
  gap: 1rem;
  padding: 1rem;
  background: #f8f9fa;
  border-radius: 8px;
  color: var(--primary-color);
  text-decoration: none;
  transition: all 0.3s ease;
}

.contact-link:hover {
  transform: translateX(10px);
  background: var(--primary-color);
  color: white;
}

.form-container {
  padding: 2rem;
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
}

.form-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.full-width {
  grid-column: 1 / -1;
}

label {
  font-size: 0.9rem;
  font-weight: 500;
  color: #444;
}

input,
textarea {
  padding: 0.8rem;
  border: 2px solid #eee;
  border-radius: 8px;
  font-size: 1rem;
  transition: all 0.3s ease;
}

input:focus,
textarea:focus {
  outline: none;
  border-color: var(--primary-color);
  box-shadow: 0 0 0 3px rgba(var(--primary-color-rgb), 0.1);
}

textarea {
  resize: vertical;
}

.form-buttons {
  display: flex;
  gap: 1rem;
  margin-top: 2rem;
}

button {
  padding: 0.8rem 2rem;
  border: none;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.btn-submit {
  background: var(--primary-color);
  color: white;
}

.btn-clear {
  background: #f8f9fa;
  color: #666;
}

button:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@media (max-width: 992px) {
  .contact-container {
    grid-template-columns: 1fr;
    gap: 2rem;
  }
}

@media (max-width: 768px) {
  .section-header {
    font-size: 2.5rem;
  }

  .form-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 480px) {
  .contact-dashboard {
    padding: 2rem 1rem;
  }

  .section-header {
    font-size: 2rem;
  }

  .contact-info,
  .form-container {
    padding: 1.5rem;
  }
}
</style>
