<template>
  <div class="contact-dashboard">
    <h2 class="section-header">Get In Touch</h2>
    
    <!-- Success Message -->
    <div v-if="successMessage" class="message success-message">
      <i class="fas fa-check-circle"></i>
      {{ successMessage }}
    </div>
    
    <!-- Error Message -->
    <div v-if="errorMessage" class="message error-message">
      <i class="fas fa-exclamation-circle"></i>
      {{ errorMessage }}
    </div>
    
    <!-- Loading Indicator -->
    <div v-if="loading" class="message loading-message">
      <i class="fas fa-spinner fa-spin"></i>
      Submitting your message...
    </div>

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
              <label for="first_name">First Name</label>
              <input
                type="text"
                id="first_name"
                v-model="formData.firstName"
                required
                :disabled="loading"
              />
              <span v-if="errors.first_name" class="error-text">{{ errors.first_name }}</span>
            </div>

            <div class="form-group">
              <label for="last_name">Last Name</label>
              <input
                type="text"
                id="last_name"
                v-model="formData.lastName"
                required
                :disabled="loading"
              />
              <span v-if="errors.last_name" class="error-text">{{ errors.last_name }}</span>
            </div>

            <div class="form-group">
              <label for="email">Email</label>
              <input
                type="email"
                id="email"
                v-model="formData.email"
                required
                :disabled="loading"
              />
              <span v-if="errors.email" class="error-text">{{ errors.email }}</span>
            </div>

            <div class="form-group">
              <label for="phone">Phone Number</label>
              <input 
                type="tel" 
                id="phone" 
                v-model="formData.phone" 
                required 
                :disabled="loading"
              />
              <span v-if="errors.phone" class="error-text">{{ errors.phone }}</span>
            </div>

            <div class="form-group full-width">
              <label for="query">Your Message</label>
              <textarea
                id="query"
                v-model="formData.query"
                required
                rows="4"
                :disabled="loading"
              ></textarea>
              <span v-if="errors.query" class="error-text">{{ errors.query }}</span>
            </div>
          </div>

          <div class="form-buttons">
            <button type="submit" class="btn-submit" :disabled="loading">
              <span v-if="loading">Sending...</span>
              <span v-else>Send Message</span>
            </button>
            <button type="button" class="btn-clear" @click="clearForm" :disabled="loading">
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
        query: ''
      },
      errors: {},
      loading: false,
      successMessage: '',
      errorMessage: ''
    };
  },
  methods: {
    async handleSubmit() {
      // Reset messages and errors
      this.errors = {};
      this.successMessage = '';
      this.errorMessage = '';
      this.loading = true;

      try {
        // Prepare data to match your Spring Boot DTO structure
        const submissionData = {
          first_name: this.formData.firstName,
          last_name: this.formData.lastName,
          email: this.formData.email,
          phone: this.formData.phone,
          query: this.formData.query
        };

        // Send data to the backend
        const baseUrl = "/api/form/submit";
        const response = await fetch(baseUrl, {
          method: 'POST',
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(submissionData),
        });

        if (!response.ok) {
          console.error("Network response was not ok", response.statusText);
          throw new Error(`Server returned ${response.status}: ${response.statusText}`);
        }

        // Parse the JSON data from the response
        const data = await response.json();

        if (response.ok) {
          this.successMessage = "Thank you! Your message has been sent successfully.";
          this.clearForm();
          
          // Auto-hide success message after 5 seconds
          setTimeout(() => {
            this.successMessage = '';
          }, 5000);
        } else {
          // Handle validation or other errors from the backend
          if (response.status === 400 || response.status === 409) {
            this.errors = data;
            this.errorMessage = "Please correct the errors below.";
          } else {
            this.errorMessage = data.error || "There was an error submitting the form.";
          }
        }
      } catch (error) {
        // Handle network errors or other exceptions
        console.error("Error:", error);
        this.errorMessage = "There was an error submitting the form. Please try again later.";
        
        // Auto-hide error message after 5 seconds
        setTimeout(() => {
          this.errorMessage = '';
        }, 5000);
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
        query: ''
      };
      this.errors = {};
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

button:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

/* Message Styles */
.message {
  padding: 1rem;
  margin: 1rem 0;
  border-radius: 8px;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  max-width: 800px;
  width: 100%;
  animation: slideDown 0.3s ease;
}

.success-message {
  background-color: #f0fff4;
  color: #2d8045;
  border: 1px solid #9ae6b4;
}

.error-message {
  background-color: #fff5f5;
  color: #c53030;
  border: 1px solid #feb2b2;
}

.loading-message {
  background-color: #ebf8ff;
  color: #2b6cb0;
  border: 1px solid #90cdf4;
}

.error-text {
  color: #c53030;
  font-size: 0.8rem;
  margin-top: 0.25rem;
  display: block;
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

@keyframes slideDown {
  from {
    opacity: 0;
    transform: translateY(-10px);
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
  
  .form-buttons {
    flex-direction: column;
  }
}
</style>