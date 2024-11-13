export class AssessmentGroup {
    assessmentNumber: number;
    clientId: number;
    //userId: number;
    objective: string;
    name: string;

    constructor() {
        this.assessmentNumber = 0;
        this.clientId = 0;
        this.objective = '';
        this.name = '';
    }

    // Add other necessary fields
    setClientId(clientId: number): void {
        this.clientId = clientId;
    }

}